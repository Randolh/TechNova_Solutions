
package campus.project.technova_solutions.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Comentario registrado en un ticket")
public class CommentResponseDto {

    @Schema(description = "ID del comentario", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Texto del comentario", example = "Se reemplazó el cable de red")
    private String contenido;

    @Schema(description = "ID del ticket al que pertenece", example = "10")
    private Long ticketId;

    @Schema(description = "ID del usuario que escribió el comentario", example = "3")
    private Long autorId;

    @Schema(description = "Nombre del autor del comentario", example = "Ana Pérez")
    private String autorNombre;

    @Schema(description = "Fecha y hora de creación del comentario", example = "2026-11-28T10:15:30", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime fechaCreacion;

    public CommentResponseDto() {
    }

    public CommentResponseDto(Long id, String contenido,
                                  Long ticketId, Long autorId,
                                  String autorNombre,
                                  LocalDateTime fechaCreacion) {

        this.id = id;
        this.contenido = contenido;
        this.ticketId = ticketId;
        this.autorId = autorId;
        this.autorNombre = autorNombre;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public String getAutorNombre() {
        return autorNombre;
    }

    public void setAutorNombre(String autorNombre) {
        this.autorNombre = autorNombre;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
