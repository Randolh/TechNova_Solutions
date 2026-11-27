package campus.project.technova_solutions.history;

import campus.project.technova_solutions.enums.EstadoTicket;

import java.time.LocalDateTime;

public record HistorialCambioResponseDto(
        Long id,
        EstadoTicket estadoAnterior,
        EstadoTicket estadoNuevo,
        String nombreResponsable,
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
