package med.voll.api.dominio.consulta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta,Long> {

    Boolean existsByPacienteIdAndHorarioBetween(Long idPaciente, LocalDateTime horarioMinimo, LocalDateTime horarioMaximo);

    Boolean existsByMedicoIdAndHorario(Long medicoId, LocalDateTime horario);



}
