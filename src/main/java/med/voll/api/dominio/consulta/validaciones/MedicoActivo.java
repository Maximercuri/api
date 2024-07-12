package med.voll.api.dominio.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.dominio.consulta.DatosAgendarConsulta;
import med.voll.api.dominio.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoActivo implements ValidadorDeConsultas {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DatosAgendarConsulta datosAgendarConsulta){

        if (datosAgendarConsulta.idMedico() == null){
            return;
        }

        var medicoActivo = medicoRepository.findActivoById(datosAgendarConsulta.idMedico());

        if (!medicoActivo){
            throw new ValidationException("Para Agendar una Consulta, el Medico Debe Estar Activo Actualmente en la Clínica");
        }

    }

}
