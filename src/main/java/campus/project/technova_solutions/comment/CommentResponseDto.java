
package campus.project.technova_solutions.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

public class ComentarioRespuestaDTO {

    private Long id;
    private String contenido;
    private Long ticketId;
    private String autor;
    private LocalDateTime fechaCreacion;

    public ComentarioRespuestaDTO() {
    }

    public ComentarioRespuestaDTO(Long id, String contenido,
                                  Long ticketId, String autor,
                                  LocalDateTime fechaCreacion) {
        this.id = id;
        this.contenido = contenido;
        this.ticketId = ticketId;
        this.autor = autor;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
