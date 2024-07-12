package med.voll.api.dominio.consulta;

import med.voll.api.dominio.consulta.validaciones.ValidadorDeCancelaciones;
import med.voll.api.dominio.consulta.validaciones.ValidadorDeConsultas;
import med.voll.api.dominio.medico.Medico;
import med.voll.api.dominio.medico.MedicoRepository;
import med.voll.api.dominio.paciente.Paciente;
import med.voll.api.dominio.paciente.PacienteRepository;
import med.voll.api.infra.errores.ExcepcionDeValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    List<ValidadorDeConsultas> validadores;

    @Autowired
    List<ValidadorDeCancelaciones> validadoresDeCancelamiento;

    public DatosDetallesDeConsulta agendar(DatosAgendarConsulta datosAgendarConsulta){

        if (pacienteRepository.findById(datosAgendarConsulta.idPaciente()).isEmpty()) {
            throw new ExcepcionDeValidacionDeIntegridad("Este Id No Corresponde a Ningún Paciente Valido");
        }

        if (datosAgendarConsulta.idMedico() != null && !medicoRepository.existsById(datosAgendarConsulta.idMedico())) {
            throw new ExcepcionDeValidacionDeIntegridad("Este Id no Corresponde a Ningún Medico Valido");
        }

        validadores.forEach(validador -> validador.validar(datosAgendarConsulta));

        Paciente paciente = pacienteRepository.findById(datosAgendarConsulta.idPaciente()).get();

        Medico medico = seleccionarMedico(datosAgendarConsulta);

        if (medico == null){
            throw new ExcepcionDeValidacionDeIntegridad("No Existen Medicos Disponibles Para la Especialidad Elegida en el Dia y Horario Elegidos");
        }

        var consulta = new Consulta(medico, paciente, datosAgendarConsulta.horario());

        consultaRepository.save(consulta);

        return new DatosDetallesDeConsulta(consulta);

    }

    public void cancelar(DatosCancelacionDeConsulta datosCancelacionDeConsulta){

        if (!consultaRepository.existsById(datosCancelacionDeConsulta.idConsulta())) {
            throw new ExcepcionDeValidacionDeIntegridad("El Id de Consulta Proveído no Existe en el Sistema");
        }

        validadoresDeCancelamiento.forEach(validador -> validador.validar(datosCancelacionDeConsulta));

        var consulta = consultaRepository.getReferenceById(datosCancelacionDeConsulta.idConsulta());

        consulta.cancelar(datosCancelacionDeConsulta.motivo());

    }

    public Medico seleccionarMedico(DatosAgendarConsulta datosAgendarConsulta){
        if (datosAgendarConsulta.idMedico() != null){
            return medicoRepository.getReferenceById(datosAgendarConsulta.idMedico());
        }
        if (datosAgendarConsulta.especialidadElegida() == null){
            throw new ExcepcionDeValidacionDeIntegridad("La Especialidad No debe Estar Vacía");
        }

        return medicoRepository.findAvailableMedico(datosAgendarConsulta.especialidadElegida(), datosAgendarConsulta.horario());
    }

}
