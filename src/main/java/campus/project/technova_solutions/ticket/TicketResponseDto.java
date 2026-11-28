package campus.project.technova_solutions.ticket;

import campus.project.technova_solutions.enums.CategoriaTicket;
import campus.project.technova_solutions.enums.EstadoTicket;
import campus.project.technova_solutions.enums.PrioridadTicket;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Información completa de un ticket de soporte")
public record TicketResponseDto(
        @Schema(description = "ID del ticket", example = "10")
        Long id,

        @Schema(description = "Título del ticket", example = "No enciende el monitor")
        String titulo,

        @Schema(description = "Descripción del problema", example = "El monitor de la oficina 204 no muestra imagen")
        String descripcion,

        @Schema(description = "Categoría del ticket", example = "HARDWARE")
        CategoriaTicket categoria,

        @Schema(description = "Prioridad del ticket", example = "ALTA")
        PrioridadTicket prioridad,

        @Schema(description = "Estado actual del ticket", example = "ABIERTA")
        EstadoTicket estado,

        @Schema(description = "ID del usuario que creó el ticket", example = "3")
        Long usuarioSolicitanteId,

        @Schema(description = "Nombre del usuario solicitante", example = "Ana Pérez")
        String nombreSolicitante,

        @Schema(description = "ID del técnico asignado (null si no hay técnico)", example = "5", nullable = true)
        Long tecnicoAsignadoId,

        @Schema(description = "Nombre del técnico asignado (null si no hay técnico)", example = "Carlos Gómez", nullable = true)
        String nombreTecnico,

        @Schema(description = "Fecha y hora de creación del ticket", example = "2026-11-28T10:00:00")
        LocalDateTime fechaCreacion,

        @Schema(description = "Fecha y hora de la última modificación", example = "2026-11-28T12:30:00")
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
