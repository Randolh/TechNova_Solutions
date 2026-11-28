
package campus.project.technova_solutions.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Datos para crear un comentario en un ticket")
public class CommentRequestDto {

    @NotBlank(message = "El comentario no puede estar vacío")

    @Schema(description = "Texto del comentario", example = "Se reemplazó el cable de red", requiredMode = Schema.RequiredMode.REQUIRED)
    private String contenido;

    public CommentRequestDto() {
    }

    public CommentRequestDto(String contenido) {
        this.contenido = contenido;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
