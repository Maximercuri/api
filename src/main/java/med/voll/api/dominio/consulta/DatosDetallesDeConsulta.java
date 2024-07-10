package med.voll.api.dominio.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosDetallesDeConsulta(Long id,
                                      Long idPaciente,
                                      Long idMedico,
                                      LocalDateTime fechaYHorario) {
}
