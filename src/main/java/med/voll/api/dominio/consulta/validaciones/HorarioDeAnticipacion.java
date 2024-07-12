package med.voll.api.dominio.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.dominio.consulta.DatosAgendarConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioDeAnticipacion implements ValidadorDeConsultas {

    public void validar(DatosAgendarConsulta datosAgendarConsulta){
        var ahora = LocalDateTime.now();
        var horaDeConsulta = datosAgendarConsulta.horario();
        var diferenciaDeMenosDe30Minutos = Duration.between(ahora, horaDeConsulta).toMinutes() < 30;

        if (diferenciaDeMenosDe30Minutos){
            throw new ValidationException("Las Consultas Deben Agendarse Con al Menos 30 Minutos de Anticipación");
        }
    }
}
