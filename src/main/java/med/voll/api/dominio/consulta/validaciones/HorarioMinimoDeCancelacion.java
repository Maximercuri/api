package med.voll.api.dominio.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.dominio.consulta.ConsultaRepository;
import med.voll.api.dominio.consulta.DatosCancelacionDeConsulta;
import med.voll.api.infra.errores.ExcepcionDeValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioMinimoDeCancelacion implements ValidadorDeCancelaciones {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosCancelacionDeConsulta datosCancelacionDeConsulta) {

        var consulta = consultaRepository.getReferenceById(datosCancelacionDeConsulta.idConsulta());
        var momentoActual = LocalDateTime.now();
        var diferenciaDeTiempo = Duration.between(momentoActual, consulta.getHorario()).toHours();

        if (diferenciaDeTiempo < 24){
            throw new ValidationException("Las Consultas Solo Pueden Ser Canceladas Con 24 Horas de Anticipación");
        }

    }
}
