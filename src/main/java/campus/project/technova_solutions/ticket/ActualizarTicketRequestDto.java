package campus.project.technova_solutions.ticket;

import campus.project.technova_solutions.enums.CategoriaTicket;
import campus.project.technova_solutions.enums.PrioridadTicket;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ActualizarTicketRequestDto(
        @NotBlank(message = "El título es obligatorio")
        @Size(min = 5, max = 150, message = "El título debe tener entre 5 y 150 caracteres")
        String titulo,

        @NotBlank(message = "La descripción es obligatoria")
        @Size(min = 10, message = "La descripción debe tener al menos 10 caracteres")
        String descripcion,

        @NotNull(message = "La categoría es obligatoria")
        CategoriaTicket categoria,

        @NotNull(message = "La prioridad es obligatoria")
        PrioridadTicket prioridad
) {}
