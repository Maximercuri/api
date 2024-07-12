package med.voll.api.dominio.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.dominio.consulta.DatosAgendarConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioDeAtencion implements ValidadorDeConsultas {

    public void validar(DatosAgendarConsulta datosAgendarConsulta){

        var domingo = DayOfWeek.SUNDAY.equals(datosAgendarConsulta.horario().getDayOfWeek());
        var antesDeAbrir = datosAgendarConsulta.horario().getHour() < 7;
        var despuesDeCierre = datosAgendarConsulta.horario().getHour() > 19;

        if(domingo){
            throw new ValidationException("El Horario de Atención de la Clínica es de Lunes a Sábado");
        }

        if(antesDeAbrir || despuesDeCierre){
            throw new ValidationException("El Horario de Atención de la Clínica es de 7 a 19");
        }

    }

}
