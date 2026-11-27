package campus.project.technova_solutions.history;

import campus.project.technova_solutions.enums.EstadoTicket;
import campus.project.technova_solutions.enums.Rol;
import campus.project.technova_solutions.ticket.Ticket;
import campus.project.technova_solutions.ticket.TicketRepository;
import campus.project.technova_solutions.user.Usuario;
import campus.project.technova_solutions.user.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistorialCambioService {

    private final HistorialCambioRepository historialCambioRepository;
    private final TicketRepository ticketRepository;
    private final UsuarioRepository usuarioRepository;

    public HistorialCambioService(HistorialCambioRepository historialCambioRepository,
                                  TicketRepository ticketRepository,
                                  UsuarioRepository usuarioRepository) {
        this.historialCambioRepository = historialCambioRepository;
        this.ticketRepository = ticketRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public HistorialCambio registrarCambio(Ticket ticket,
                                           EstadoTicket estadoAnterior,
                                           EstadoTicket estadoNuevo,
                                           Usuario usuarioResponsable,
                                           LocalDateTime fechaCambio) {
        if (ticket == null || usuarioResponsable == null) {
            throw new RuntimeException("El ticket y el usuario responsable son obligatorios para registrar el historial");
        }
        if (estadoAnterior == null || estadoNuevo == null) {
            throw new RuntimeException("Los estados anterior y nuevo son obligatorios para registrar el historial");
        }

        LocalDateTime fecha = fechaCambio != null ? fechaCambio : LocalDateTime.now();

        HistorialCambio historial = new HistorialCambio(
                estadoAnterior,
                estadoNuevo,
                ticket,
                usuarioResponsable,
                fecha
        );
        return historialCambioRepository.save(historial);
    }

    @Transactional(readOnly = true)
    public List<HistorialCambioResponseDto> obtenerHistorialPorTicket(Long ticketId, Long usuarioActualId) {
        Usuario usuarioActual = usuarioRepository.findById(usuarioActualId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + usuarioActualId));

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado con id: " + ticketId));

        validarAccesoHistorial(ticket, usuarioActual);

        return historialCambioRepository.findByTicketIdOrderByFechaCambioAsc(ticketId)
                .stream()
                .map(HistorialCambioResponseDto::desde)
                .toList();
    }

    private void validarAccesoHistorial(Ticket ticket, Usuario usuarioActual) {
        if (usuarioActual.getRol() == Rol.ADMIN) {
            return;
        }
        if (usuarioActual.getRol() == Rol.TECHNICIAN) {
            if (ticket.getTecnicoAsignado() == null
                    || !ticket.getTecnicoAsignado().getId().equals(usuarioActual.getId())) {
                throw new RuntimeException("No tiene permisos para consultar el historial de este ticket");
            }
            return;
        }
        if (!ticket.getUsuarioSolicitante().getId().equals(usuarioActual.getId())) {
            throw new RuntimeException("No tiene permisos para consultar el historial de este ticket");
        }
    }
}
