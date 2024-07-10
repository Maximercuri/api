package med.voll.api.dominio.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico,Long> {

    Page<Medico> findByActivoTrue(Pageable pagina);

    @Query("SELECT medico FROM Medico medico WHERE medico.activo=TRUE AND especialidad = :especialidad AND medico.id NOT IN (consulta.medico.id FROM Consulta consulta WHERE consulta.fechaYHorario = :fechaYHorario) ORDER BY RAND() LIMIT 1")
    Medico seleccionarMedicoRandomConEspecialidadElegidaEnFecha(Especialidad especialidad, LocalDateTime fechaYHorario);
}
