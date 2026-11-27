package campus.project.technova_solutions.ticket;

import campus.project.technova_solutions.enums.EstadoTicket;
import jakarta.validation.constraints.NotNull;

public record CambiarEstadoRequestDto(
        @NotNull(message = "El nuevo estado es obligatorio")
        EstadoTicket estado
) {}
