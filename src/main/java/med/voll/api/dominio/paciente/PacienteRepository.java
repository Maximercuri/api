package med.voll.api.dominio.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {

    Page<Paciente> findByActualTrue(Pageable pagina);

    @Query("SELECT p.actual FROM Paciente p WHERE p.id = :idPaciente")
    Boolean findActivoById(Long idPaciente);
}
