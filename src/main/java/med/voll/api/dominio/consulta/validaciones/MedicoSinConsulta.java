package med.voll.api.dominio.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.dominio.consulta.ConsultaRepository;
import med.voll.api.dominio.consulta.DatosAgendarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoSinConsulta implements ValidadorDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosAgendarConsulta datosAgendarConsulta){

        if (datosAgendarConsulta == null){
            return;
        }

        var medicoConConsulta = consultaRepository.existsByMedicoIdAndHorario(datosAgendarConsulta.idMedico(), datosAgendarConsulta.horario());

        if (medicoConConsulta) {
            throw new ValidationException("El Medico ya Tiene Una Consulta Asignada Para Ese Día y Horario");
        }

    }

}