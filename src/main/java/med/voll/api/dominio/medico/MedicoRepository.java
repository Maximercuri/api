package med.voll.api.dominio.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico,Long> {

    Page<Medico> findByActivoTrue(Pageable pagina);

    @Query("SELECT m FROM Medico m WHERE m.activo = TRUE AND m.especialidad = :especialidad AND m.id NOT IN (SELECT c.medico.id FROM Consulta c WHERE c.horario = :horario) ORDER BY RANDOM() LIMIT 1")
    Medico findAvailableMedico(Especialidad especialidad, LocalDateTime horario);

    @Query("SELECT m.activo from Medico m where m.id = :idMedico")
    Boolean findActivoById(Long idMedico);
}
