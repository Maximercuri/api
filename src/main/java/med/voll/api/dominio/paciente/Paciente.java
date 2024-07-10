package med.voll.api.dominio.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.direccion.Direccion;

@Entity(name = "Paciente")
@Table(name = "pacientes")
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String documento;
    private String telefono;
    private boolean actual;

    @Embedded
    private Direccion direccion;

    public Paciente(DatosRegistroPaciente datosRegistroPaciente) {
        this.nombre = datosRegistroPaciente.nombre();
        this.email = datosRegistroPaciente.email();
        this.telefono = datosRegistroPaciente.telefono();
        this.documento = datosRegistroPaciente.documento();
        this.direccion = new Direccion(datosRegistroPaciente.direccion());
    }

    public void actualizarDatos(DatosActualizaciónPaciente datosActualizacionPaciente) {
        if(datosActualizacionPaciente.nombre() != null && !datosActualizacionPaciente.nombre().isEmpty()){
            this.nombre = datosActualizacionPaciente.nombre();
        };
        if (datosActualizacionPaciente.telefono() != null && !datosActualizacionPaciente.telefono().isEmpty()) {
            this.telefono = datosActualizacionPaciente.telefono();
        }
        if (datosActualizacionPaciente.email() != null && !datosActualizacionPaciente.email().isEmpty()){
            this.email = datosActualizacionPaciente.email();
        }
        if (datosActualizacionPaciente.direccion() != null) {
            this.direccion = direccion.actualizarDatos(datosActualizacionPaciente.direccion());
        }
    }

    public void desactivarPaciente() {
        this.actual = false;
    }
}
