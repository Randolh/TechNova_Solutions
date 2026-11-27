
package campus.project.technova_solutions.user;

import campus.project.technova_solutions.enums.Rol;
import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioRespuestaDTO {

    private Long id;
    private String nombre;
    private String email;
    private Rol rol;

    public UsuarioRespuestaDTO() {
    }

    public UsuarioRespuestaDTO(Long id, String nombre,
                               String email, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public Rol getRol() {
        return rol;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
