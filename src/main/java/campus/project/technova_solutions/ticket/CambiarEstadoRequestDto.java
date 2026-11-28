package campus.project.technova_solutions.ticket;

import campus.project.technova_solutions.enums.EstadoTicket;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Datos para cambiar el estado de un ticket")
public record CambiarEstadoRequestDto(
        @NotNull(message = "El nuevo estado es obligatorio")
        @Schema(description = "Nuevo estado del ticket", example = "EN_PROCESO", requiredMode = Schema.RequiredMode.REQUIRED)
        EstadoTicket estado
) {}
