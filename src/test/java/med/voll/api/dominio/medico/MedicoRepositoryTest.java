package med.voll.api.dominio.medico;

import med.voll.api.direccion.DatosDireccion;
import med.voll.api.dominio.consulta.Consulta;
import med.voll.api.dominio.paciente.DatosRegistroPaciente;
import med.voll.api.dominio.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Debería Retornar Nulo Cuando El Medico Se Encuentra En Otra Consulta En Ese Horario")
    void findAvailableMedicoEscenario1() {

        var proximoLunes10hs = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var medico = registrarMedico("Jose Rojas", "JR@gmail.com", "23456789", Especialidad.CARDIOLOGIA);
        var paciente = registrarPaciente("Juan Perez", "JP12@gmail.com", "98765432");
        registrarConsulta(medico, paciente, proximoLunes10hs);

        var medicoLibre = medicoRepository.findAvailableMedico(Especialidad.CARDIOLOGIA, proximoLunes10hs);

        assertThat(medicoLibre).isNull();

    }

    @Test
    @DisplayName("Debería Retornar Un Medico Cuando Realice La Consulta En La Base De Datos Para Ese Horario")
    void findAvailableMedicoEscenario2() {

        var proximoLunes10hs = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var medico = registrarMedico("Jose Rojas", "JR@gmail.com", "23456789", Especialidad.CARDIOLOGIA);

        var medicoLibre = medicoRepository.findAvailableMedico(Especialidad.CARDIOLOGIA, proximoLunes10hs);

        assertThat(medicoLibre).isEqualTo(medico);

    }

    private void registrarConsulta(Medico medico, Paciente paciente, LocalDateTime fecha) {
        em.persist(new Consulta(null, medico, paciente, fecha, null));
    }

    private Medico registrarMedico(String nombre, String email, String documento, Especialidad especialidad) {
        var medico = new Medico(datosMedico(nombre, email, documento, especialidad));
        em.persist(medico);
        return medico;
    }

    private Paciente registrarPaciente(String nombre, String email, String documento) {
        var paciente = new Paciente(datosPaciente(nombre, email, documento));
        em.persist(paciente);
        return paciente;
    }

    private DatosRegistroMedico datosMedico(String nombre, String email, String documento, Especialidad especialidad) {
        return new DatosRegistroMedico(
                nombre,
                email,
                "19999999",
                documento,
                especialidad,
                datosDireccion() );
    }

    private DatosRegistroPaciente datosPaciente(String nombre, String email, String documento){
        return new DatosRegistroPaciente(
                nombre,
                email,
                "23456789",
                documento,
                datosDireccion() );
    }

    private DatosDireccion datosDireccion(){
        return new DatosDireccion(
                "calle",
                "5678",
                "D",
                "barrio",
                "ciudad");
    }

}





