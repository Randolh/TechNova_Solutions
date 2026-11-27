package campus.project.technova_solutions.ticket;

import campus.project.technova_solutions.enums.CategoriaTicket;
import campus.project.technova_solutions.enums.EstadoTicket;
import campus.project.technova_solutions.enums.PrioridadTicket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("""
            SELECT t FROM Ticket t
            WHERE (:usuarioSolicitanteId IS NULL OR t.usuarioSolicitante.id = :usuarioSolicitanteId)
              AND (:tecnicoAsignadoId IS NULL OR t.tecnicoAsignado.id = :tecnicoAsignadoId)
              AND (:estado IS NULL OR t.estado = :estado)
              AND (:prioridad IS NULL OR t.prioridad = :prioridad)
              AND (:categoria IS NULL OR t.categoria = :categoria)
            """)
    Page<Ticket> buscarFiltrados(
            @Param("usuarioSolicitanteId") Long usuarioSolicitanteId,
            @Param("tecnicoAsignadoId") Long tecnicoAsignadoId,
            @Param("estado") EstadoTicket estado,
            @Param("prioridad") PrioridadTicket prioridad,
            @Param("categoria") CategoriaTicket categoria,
            Pageable pageable);

    long countByEstado(EstadoTicket estado);

    long countByUsuarioSolicitanteId(Long usuarioSolicitanteId);

    long countByUsuarioSolicitanteIdAndEstado(Long usuarioSolicitanteId, EstadoTicket estado);

    long countByTecnicoAsignadoId(Long tecnicoAsignadoId);

    long countByTecnicoAsignadoIdAndEstado(Long tecnicoAsignadoId, EstadoTicket estado);

    List<Ticket> findByEstadoIn(List<EstadoTicket> estados);

    @Query("""
            SELECT t.tecnicoAsignado.id, t.tecnicoAsignado.nombre,
                   COUNT(t),
                   SUM(CASE WHEN t.estado IN (
                       campus.project.technova_solutions.enums.EstadoTicket.RESUELTA,
                       campus.project.technova_solutions.enums.EstadoTicket.CERRADA
                   ) THEN 1 ELSE 0 END)
            FROM Ticket t
            WHERE t.tecnicoAsignado IS NOT NULL
            GROUP BY t.tecnicoAsignado.id, t.tecnicoAsignado.nombre
            """)
    List<Object[]> contarTicketsPorTecnico();
}
