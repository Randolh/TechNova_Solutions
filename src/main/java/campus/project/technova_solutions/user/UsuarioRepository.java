
package campus.project.technova_solutions.user;

import campus.project.technova_solutions.user.Usuario;
import campus.project.technova_solutions.enums.Rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Usuario> findByRol(Rol rol);

    boolean existsByRol(Rol rol);
}


