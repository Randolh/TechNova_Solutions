
package campus.project.technova_solutions.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

public class CommentResponseDto {

    private Long id;
    private String contenido;
    private Long ticketId;
    private Long autorId;
    private String autorNombre;
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
