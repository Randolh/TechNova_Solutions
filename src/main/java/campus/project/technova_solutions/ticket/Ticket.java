
package campus.project.technova_solutions.ticket;

import campus.project.technova_solutions.user.Usuario;
import campus.project.technova_solutions.comment.Comentario;
import campus.project.technova_solutions.history.HistorialCambio;
import campus.project.technova_solutions.enums.CategoriaTicket;
import campus.project.technova_solutions.enums.PrioridadTicket;
import campus.project.technova_solutions.enums.EstadoTicket;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private CategoriaTicket categoria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PrioridadTicket prioridad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoTicket estado;

    // Usuario que solicita el ticket
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuarioSolicitante;

    // Técnico asignado al ticket
    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Usuario tecnicoAsignado;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    // Comentarios del ticket
    @OneToMany(mappedBy = "ticket")
    private Set<Comentario> comentarios = new HashSet<>();

    // Historial del ticket
    @OneToMany(mappedBy = "ticket")
    private Set<HistorialCambio> historial = new HashSet<>();

    public Ticket() {
    }

    public Ticket(String titulo, String descripcion,
                  CategoriaTicket categoria,
                  PrioridadTicket prioridad,
                  EstadoTicket estado,
                  Usuario usuarioSolicitante,
                  Usuario tecnicoAsignado,
                  LocalDateTime fechaCreacion) {

        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.prioridad = prioridad;
        this.estado = estado;
        this.usuarioSolicitante = usuarioSolicitante;
        this.tecnicoAsignado = tecnicoAsignado;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CategoriaTicket getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaTicket categoria) {
        this.categoria = categoria;
    }

    public PrioridadTicket getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(PrioridadTicket prioridad) {
        this.prioridad = prioridad;
    }

    public EstadoTicket getEstado() {
        return estado;
    }

    public void setEstado(EstadoTicket estado) {
        this.estado = estado;
    }

    public Usuario getUsuarioSolicitante() {
        return usuarioSolicitante;
    }

    public void setUsuarioSolicitante(Usuario usuarioSolicitante) {
        this.usuarioSolicitante = usuarioSolicitante;
    }

    public Usuario getTecnicoAsignado() {
        return tecnicoAsignado;
    }

    public void setTecnicoAsignado(Usuario tecnicoAsignado) {
        this.tecnicoAsignado = tecnicoAsignado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Set<HistorialCambio> getHistorial() {
        return historial;
    }

    public void setHistorial(Set<HistorialCambio> historial) {
        this.historial = historial;
    }
}
