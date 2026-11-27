package campus.project.technova_solutions.ticket;

import campus.project.technova_solutions.enums.CategoriaTicket;
import campus.project.technova_solutions.enums.EstadoTicket;
import campus.project.technova_solutions.enums.PrioridadTicket;

import java.time.LocalDateTime;

public record TicketResponseDto(
        Long id,
        String titulo,
        String descripcion,
        CategoriaTicket categoria,
        PrioridadTicket prioridad,
        EstadoTicket estado,
        Long usuarioSolicitanteId,
        String nombreSolicitante,
        Long tecnicoAsignadoId,
        String nombreTecnico,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaActualizacion
) {
    public static TicketResponseDto desde(Ticket ticket) {
        return new TicketResponseDto(
                ticket.getId(),
                ticket.getTitulo(),
                ticket.getDescripcion(),
                ticket.getCategoria(),
                ticket.getPrioridad(),
                ticket.getEstado(),
                ticket.getUsuarioSolicitante().getId(),
                ticket.getUsuarioSolicitante().getNombre(),
                ticket.getTecnicoAsignado() != null ? ticket.getTecnicoAsignado().getId() : null,
                ticket.getTecnicoAsignado() != null ? ticket.getTecnicoAsignado().getNombre() : null,
                ticket.getFechaCreacion(),
                ticket.getFechaActualizacion()
        );
    }
}
