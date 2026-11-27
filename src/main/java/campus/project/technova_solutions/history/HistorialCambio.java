
package campus.project.technova_solutions.history;

import campus.project.technova_solutions.ticket.Ticket;
import campus.project.technova_solutions.user.Usuario;
import campus.project.technova_solutions.enums.EstadoTicket;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_cambios")
public class HistorialCambio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_anterior", nullable = false, length = 20)
    private EstadoTicket estadoAnterior;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_nuevo", nullable = false, length = 20)
    private EstadoTicket estadoNuevo;

    // Ticket al que corresponde el cambio
    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    // Usuario que realizó el cambio
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuarioResponsable;

    @Column(name = "fecha_cambio")
    private LocalDateTime fechaCambio;

    public HistorialCambio() {
    }

    public HistorialCambio(EstadoTicket estadoAnterior,
                           EstadoTicket estadoNuevo,
                           Ticket ticket,
                           Usuario usuarioResponsable,
                           LocalDateTime fechaCambio) {

        this.estadoAnterior = estadoAnterior;
        this.estadoNuevo = estadoNuevo;
        this.ticket = ticket;
        this.usuarioResponsable = usuarioResponsable;
        this.fechaCambio = fechaCambio;
    }

    public Long getId() {
        return id;
    }

    public EstadoTicket getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(EstadoTicket estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public EstadoTicket getEstadoNuevo() {
        return estadoNuevo;
    }

    public void setEstadoNuevo(EstadoTicket estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Usuario getUsuarioResponsable() {
        return usuarioResponsable;
    }

    public void setUsuarioResponsable(Usuario usuarioResponsable) {
        this.usuarioResponsable = usuarioResponsable;
    }

    public LocalDateTime getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(LocalDateTime fechaCambio) {
        this.fechaCambio = fechaCambio;
    }
}
