
package campus.project.technova_solutions.user;

import campus.project.technova_solutions.enums.Rol;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información pública de un usuario")
public class UserResponseDto {

    @Schema(description = "ID del usuario", example = "3", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nombre completo del usuario", example = "Ana Pérez")
    private String nombre;

    @Schema(description = "Correo electrónico del usuario", example = "ana.perez@technova.com", format = "email")
    private String email;

    @Schema(description = "Rol del usuario en el sistema", example = "USER")
    private Rol rol;

    public UserResponseDto() {
    }

    public UserResponseDto(Long id, String nombre,
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
