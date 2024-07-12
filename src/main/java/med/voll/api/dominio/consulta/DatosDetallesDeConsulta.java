package med.voll.api.dominio.consulta;

import java.time.LocalDateTime;

public record DatosDetallesDeConsulta(Long id,
                                      Long idPaciente,
                                      Long idMedico,
                                      LocalDateTime horario) {

    public DatosDetallesDeConsulta(Consulta consulta) {
        this(consulta.getId(),
             consulta.getPaciente().getId(),
             consulta.getMedico().getId(),
             consulta.getHorario());
    }
}
