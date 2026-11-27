package campus.project.technova_solutions.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialCambioRepository extends JpaRepository<HistorialCambio, Long> {

    List<HistorialCambio> findByTicketIdOrderByFechaCambioAsc(Long ticketId);
}
