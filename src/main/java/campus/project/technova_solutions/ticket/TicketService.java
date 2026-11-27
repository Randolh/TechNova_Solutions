package campus.project.technova_solutions.ticket;

import campus.project.technova_solutions.enums.CategoriaTicket;
import campus.project.technova_solutions.enums.EstadoTicket;
import campus.project.technova_solutions.enums.PrioridadTicket;
import campus.project.technova_solutions.enums.Rol;
import campus.project.technova_solutions.history.HistorialCambio;
import campus.project.technova_solutions.history.HistorialCambioRepository;
import campus.project.technova_solutions.user.Usuario;
import campus.project.technova_solutions.user.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UsuarioRepository usuarioRepository;
    private final HistorialCambioRepository historialCambioRepository;

    public TicketService(TicketRepository ticketRepository,
                         UsuarioRepository usuarioRepository,
                         HistorialCambioRepository historialCambioRepository) {
        this.ticketRepository = ticketRepository;
        this.usuarioRepository = usuarioRepository;
        this.historialCambioRepository = historialCambioRepository;
    }

    @Transactional
    public TicketResponseDto crearTicket(CrearTicketRequestDto request, Long usuarioSolicitanteId) {
        Usuario solicitante = buscarUsuarioPorId(usuarioSolicitanteId);
        LocalDateTime ahora = LocalDateTime.now();

        Ticket ticket = new Ticket(
                request.titulo(),
                request.descripcion(),
                request.categoria(),
                request.prioridad(),
                EstadoTicket.ABIERTA,
                solicitante,
                null,
                ahora
        );
        ticket.setFechaActualizacion(ahora);

        Ticket guardado = ticketRepository.save(ticket);
        return TicketResponseDto.desde(guardado);
    }

    @Transactional(readOnly = true)
    public Page<TicketResponseDto> listarTickets(Long usuarioActualId,
                                                 Long usuarioSolicitanteId,
                                                 Long tecnicoAsignadoId,
                                                 EstadoTicket estado,
                                                 PrioridadTicket prioridad,
                                                 CategoriaTicket categoria,
                                                 Pageable pageable) {
        Usuario usuarioActual = buscarUsuarioPorId(usuarioActualId);

        Long filtroSolicitante = usuarioSolicitanteId;
        Long filtroTecnico = tecnicoAsignadoId;

        if (usuarioActual.getRol() == Rol.USER) {
            filtroSolicitante = usuarioActual.getId();
            filtroTecnico = null;
        } else if (usuarioActual.getRol() == Rol.TECHNICIAN) {
            filtroTecnico = usuarioActual.getId();
            filtroSolicitante = null;
        }

        return ticketRepository.buscarFiltrados(
                filtroSolicitante,
                filtroTecnico,
                estado,
                prioridad,
                categoria,
                pageable
        ).map(TicketResponseDto::desde);
    }

    @Transactional(readOnly = true)
    public TicketResponseDto obtenerPorId(Long ticketId, Long usuarioActualId) {
        Usuario usuarioActual = buscarUsuarioPorId(usuarioActualId);
        Ticket ticket = buscarTicketPorId(ticketId);
        validarAccesoLectura(ticket, usuarioActual);
        return TicketResponseDto.desde(ticket);
    }

    @Transactional
    public TicketResponseDto actualizarTicket(Long ticketId,
                                              ActualizarTicketRequestDto request,
                                              Long usuarioActualId) {
        Usuario usuarioActual = buscarUsuarioPorId(usuarioActualId);
        Ticket ticket = buscarTicketPorId(ticketId);

        if (!ticket.getUsuarioSolicitante().getId().equals(usuarioActual.getId())) {
            throw new RuntimeException("Solo el usuario solicitante puede modificar este ticket");
        }
        if (ticket.getEstado() != EstadoTicket.ABIERTA || ticket.getTecnicoAsignado() != null) {
            throw new RuntimeException("El ticket solo puede modificarse en estado ABIERTA y sin técnico asignado");
        }

        ticket.setTitulo(request.titulo());
        ticket.setDescripcion(request.descripcion());
        ticket.setCategoria(request.categoria());
        ticket.setPrioridad(request.prioridad());
        ticket.setFechaActualizacion(LocalDateTime.now());

        Ticket actualizado = ticketRepository.save(ticket);
        return TicketResponseDto.desde(actualizado);
    }

    @Transactional
    public TicketResponseDto asignarTecnico(Long ticketId,
                                          AsignarTecnicoRequestDto request,
                                          Long adminId) {
        Usuario admin = buscarUsuarioPorId(adminId);
        if (admin.getRol() != Rol.ADMIN) {
            throw new RuntimeException("Solo un administrador puede asignar técnicos a los tickets");
        }

        Ticket ticket = buscarTicketPorId(ticketId);
        if (ticket.getEstado() != EstadoTicket.ABIERTA && ticket.getEstado() != EstadoTicket.ASIGNADA) {
            throw new RuntimeException("Solo se pueden asignar o reasignar tickets en estado ABIERTA o ASIGNADA");
        }

        Usuario tecnico = buscarUsuarioPorId(request.tecnicoId());
        if (tecnico.getRol() != Rol.TECHNICIAN) {
            throw new RuntimeException("El usuario seleccionado no tiene el rol TECHNICIAN");
        }

        LocalDateTime ahora = LocalDateTime.now();
        EstadoTicket estadoAnterior = ticket.getEstado();

        ticket.setTecnicoAsignado(tecnico);
        ticket.setEstado(EstadoTicket.ASIGNADA);
        ticket.setFechaActualizacion(ahora);

        Ticket actualizado = ticketRepository.save(ticket);

        if (estadoAnterior != EstadoTicket.ASIGNADA) {
            HistorialCambio historial = new HistorialCambio(
                    estadoAnterior,
                    EstadoTicket.ASIGNADA,
                    actualizado,
                    admin,
                    ahora
            );
            historialCambioRepository.save(historial);
        }

        return TicketResponseDto.desde(actualizado);
    }

    @Transactional
    public TicketResponseDto cambiarEstado(Long ticketId,
                                           CambiarEstadoRequestDto request,
                                           Long usuarioActualId) {
        Usuario usuarioActual = buscarUsuarioPorId(usuarioActualId);
        Ticket ticket = buscarTicketPorId(ticketId);

        EstadoTicket estadoActual = ticket.getEstado();
        EstadoTicket nuevoEstado = request.estado();

        if (estadoActual == EstadoTicket.CERRADA) {
            throw new RuntimeException("No se puede cambiar el estado de un ticket que ya está CERRADA");
        }

        validarTransicionEstado(ticket, usuarioActual, estadoActual, nuevoEstado);

        LocalDateTime ahora = LocalDateTime.now();
        ticket.setEstado(nuevoEstado);
        ticket.setFechaActualizacion(ahora);

        Ticket actualizado = ticketRepository.save(ticket);

        HistorialCambio historial = new HistorialCambio(
                estadoActual,
                nuevoEstado,
                actualizado,
                usuarioActual,
                ahora
        );
        historialCambioRepository.save(historial);

        return TicketResponseDto.desde(actualizado);
    }

    private void validarAccesoLectura(Ticket ticket, Usuario usuarioActual) {
        if (usuarioActual.getRol() == Rol.ADMIN) {
            return;
        }
        if (usuarioActual.getRol() == Rol.TECHNICIAN) {
            if (ticket.getTecnicoAsignado() == null
                    || !ticket.getTecnicoAsignado().getId().equals(usuarioActual.getId())) {
                throw new RuntimeException("No tiene permisos para acceder a este ticket");
            }
            return;
        }
        if (!ticket.getUsuarioSolicitante().getId().equals(usuarioActual.getId())) {
            throw new RuntimeException("No tiene permisos para acceder a este ticket");
        }
    }

    private void validarTransicionEstado(Ticket ticket,
                                         Usuario usuarioActual,
                                         EstadoTicket estadoActual,
                                         EstadoTicket nuevoEstado) {
        if (estadoActual == EstadoTicket.ASIGNADA && nuevoEstado == EstadoTicket.EN_PROCESO) {
            validarTecnicoAsignado(ticket, usuarioActual);
            return;
        }
        if (estadoActual == EstadoTicket.EN_PROCESO && nuevoEstado == EstadoTicket.RESUELTA) {
            validarTecnicoAsignado(ticket, usuarioActual);
            return;
        }
        if (estadoActual == EstadoTicket.RESUELTA && nuevoEstado == EstadoTicket.CERRADA) {
            if (usuarioActual.getRol() != Rol.USER
                    || !ticket.getUsuarioSolicitante().getId().equals(usuarioActual.getId())) {
                throw new RuntimeException("Solo el usuario solicitante puede cerrar un ticket resuelto");
            }
            return;
        }

        throw new RuntimeException("Transición de estado no permitida: " + estadoActual + " -> " + nuevoEstado);
    }

    private void validarTecnicoAsignado(Ticket ticket, Usuario usuarioActual) {
        if (usuarioActual.getRol() != Rol.TECHNICIAN
                || ticket.getTecnicoAsignado() == null
                || !ticket.getTecnicoAsignado().getId().equals(usuarioActual.getId())) {
            throw new RuntimeException("Solo el técnico asignado puede realizar esta transición de estado");
        }
    }

    private Ticket buscarTicketPorId(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado con id: " + ticketId));
    }

    private Usuario buscarUsuarioPorId(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + usuarioId));
    }
}
