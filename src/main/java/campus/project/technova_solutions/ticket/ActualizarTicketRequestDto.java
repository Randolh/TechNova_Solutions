package campus.project.technova_solutions.ticket;

import campus.project.technova_solutions.enums.CategoriaTicket;
import campus.project.technova_solutions.enums.PrioridadTicket;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Datos para actualizar un ticket existente")
public record ActualizarTicketRequestDto(
        @NotBlank(message = "El título es obligatorio")
        @Size(min = 5, max = 150, message = "El título debe tener entre 5 y 150 caracteres")
        @Schema(description = "Título breve del problema", example = "No enciende el monitor", minLength = 5, maxLength = 150, requiredMode = Schema.RequiredMode.REQUIRED)
        String titulo,

        @NotBlank(message = "La descripción es obligatoria")
        @Size(min = 10, message = "La descripción debe tener al menos 10 caracteres")
        @Schema(description = "Descripción detallada del problema", example = "El monitor de la oficina 204 no muestra imagen desde esta mañana", minLength = 10, requiredMode = Schema.RequiredMode.REQUIRED)
        String descripcion,

        @NotNull(message = "La categoría es obligatoria")
        @Schema(description = "Categoría del ticket", example = "HARDWARE", requiredMode = Schema.RequiredMode.REQUIRED)
        CategoriaTicket categoria,

        @NotNull(message = "La prioridad es obligatoria")
        @Schema(description = "Prioridad del ticket", example = "ALTA", requiredMode = Schema.RequiredMode.REQUIRED)
        PrioridadTicket prioridad
) {}
