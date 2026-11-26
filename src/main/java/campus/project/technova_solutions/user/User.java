
package campus.project.technova_solutions.user;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 20)
    private String rol;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;


    @OneToMany(mappedBy = "usuarioSolicitante")
    private Set<Ticket> ticketsSolicitados = new HashSet<>();


    @OneToMany(mappedBy = "tecnicoAsignado")
    private Set<Ticket> ticketsAsignados = new HashSet<>();

    
    @OneToMany(mappedBy = "autor")
    private Set<Comentario> comentarios = new HashSet<>();

    
    @OneToMany(mappedBy = "usuarioResponsable")
    private Set<HistorialCambio> cambiosRealizados = new HashSet<>();

    public User() {
    }

    public User(String nombre, String email, String password,
                   String rol, LocalDateTime fechaCreacion) {

        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Set<Ticket> getTicketsSolicitados() {
        return ticketsSolicitados;
    }

    public void setTicketsSolicitados(Set<Ticket> ticketsSolicitados) {
        this.ticketsSolicitados = ticketsSolicitados;
    }

    public Set<Ticket> getTicketsAsignados() {
        return ticketsAsignados;
    }

    public void setTicketsAsignados(Set<Ticket> ticketsAsignados) {
        this.ticketsAsignados = ticketsAsignados;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Set<HistorialCambio> getCambiosRealizados() {
        return cambiosRealizados;
    }

    public void setCambiosRealizados(Set<HistorialCambio> cambiosRealizados) {
        this.cambiosRealizados = cambiosRealizados;
    }
}

