package med.voll.api.dominio.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.dominio.consulta.ConsultaRepository;
import med.voll.api.dominio.consulta.DatosAgendarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSinConsultaEnElDia implements ValidadorDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosAgendarConsulta datosAgendarConsulta){
        var horarioMinimo = datosAgendarConsulta.horario().withHour(7);
        var horarioMaximo = datosAgendarConsulta.horario().withHour(18);
        var pacienteConConsulta = consultaRepository.existsByPacienteIdAndHorarioBetween(datosAgendarConsulta.idPaciente(), horarioMinimo, horarioMaximo);
        if (pacienteConConsulta) {
            throw new ValidationException("Solo se Puede Agendar Una Sola Consulta Para Cierto Dia Por Paciente");
        }
    }

}
