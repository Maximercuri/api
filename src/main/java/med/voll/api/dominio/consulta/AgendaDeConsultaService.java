package med.voll.api.dominio.consulta;

import med.voll.api.dominio.medico.Medico;
import med.voll.api.dominio.medico.MedicoRepository;
import med.voll.api.dominio.paciente.Paciente;
import med.voll.api.dominio.paciente.PacienteRepository;
import med.voll.api.infra.errores.ExcepcionDeValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    public void agendar(DatosAgendarConsulta datosAgendarConsulta){

        if (pacienteRepository.findById(datosAgendarConsulta.idPaciente()).isEmpty()) {
            throw new ExcepcionDeValidacionDeIntegridad("Este Id No Corresponde a Ningún Paciente Valido");
        }

        if (datosAgendarConsulta.idMedico() != null && medicoRepository.existsById(datosAgendarConsulta.idMedico())) {
            throw new ExcepcionDeValidacionDeIntegridad("Este Id no Corresponde a Ningún Medico Valido");
        }

        Paciente paciente = pacienteRepository.findById(datosAgendarConsulta.idPaciente()).get();

        Medico medico = seleccionarMedico(datosAgendarConsulta);

        var consulta = new Consulta(null, medico, paciente, datosAgendarConsulta.fechaYHorario());

        consultaRepository.save(consulta);

    }

    public Medico seleccionarMedico(DatosAgendarConsulta datosAgendarConsulta){
        if (datosAgendarConsulta.idMedico() != null){
            return medicoRepository.getReferenceById(datosAgendarConsulta.idMedico());
        }
        if (datosAgendarConsulta.especialidadElegida() == null){
            throw new ExcepcionDeValidacionDeIntegridad("La Especialidad No debe Estar Vacía");
        }

        return medicoRepository.seleccionarMedicoRandomConEspecialidadElegidaEnFecha(datosAgendarConsulta.especialidadElegida(), datosAgendarConsulta.fechaYHorario());
    }

}
