package campus.project.technova_solutions.ticket;

import jakarta.validation.constraints.NotNull;

public record AsignarTecnicoRequestDto(
        @NotNull(message = "El identificador del técnico es obligatorio")
        Long tecnicoId
) {}
