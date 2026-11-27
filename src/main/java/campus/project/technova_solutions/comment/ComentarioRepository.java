
package campus.project.technova_solutions.comment;

import campus.project.technova_solutions.comment.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findByTicketIdOrderByFechaCreacionAsc(Long ticketId);

    List<Comentario> findByAutorId(Long autorId);
}

