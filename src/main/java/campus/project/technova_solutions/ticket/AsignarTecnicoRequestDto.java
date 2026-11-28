package campus.project.technova_solutions.ticket;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Datos para asignar un técnico a un ticket")
public record AsignarTecnicoRequestDto(
        @NotNull(message = "El identificador del técnico es obligatorio")
        @Schema(description = "ID del usuario con rol TECHNICIAN que atenderá el ticket", example = "5", requiredMode = Schema.RequiredMode.REQUIRED)
        Long tecnicoId
) {}
