package campus.project.technova_solutions.history;

import campus.project.technova_solutions.enums.EstadoTicket;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Registro de un cambio de estado de un ticket")
public record HistorialCambioResponseDto(
        @Schema(description = "ID del registro de historial", example = "1")
        Long id,

        @Schema(description = "Estado del ticket antes del cambio", example = "ABIERTA")
        EstadoTicket estadoAnterior,

        @Schema(description = "Estado del ticket después del cambio", example = "ASIGNADA")
        EstadoTicket estadoNuevo,

        @Schema(description = "Nombre del usuario que realizó el cambio", example = "Carlos Gómez")
        String nombreResponsable,

        @Schema(description = "Fecha y hora del cambio", example = "2026-11-28T11:00:00")
        LocalDateTime fechaCambio
) {
    public static HistorialCambioResponseDto desde(HistorialCambio historial) {
        return new HistorialCambioResponseDto(
                historial.getId(),
                historial.getEstadoAnterior(),
                historial.getEstadoNuevo(),
                historial.getUsuarioResponsable().getNombre(),
                historial.getFechaCambio()
        );
    }
}
