package med.voll.api.dominio.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.direccion.Direccion;

@Entity(name = "Medico")
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    private boolean activo;

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistroMedico datosRegistroMedico) {
        this.activo = true;
        this.nombre = datosRegistroMedico.nombre();
        this.email = datosRegistroMedico.email();
        this.telefono = datosRegistroMedico.telefono();
        this.documento = datosRegistroMedico.documento();
        this.especialidad = datosRegistroMedico.especialidad();
        this.direccion = new Direccion(datosRegistroMedico.direccion());
    }

    public void actualizarDatos(DatosActualizaciónMedico datosActualizacionMedico) {
        if(datosActualizacionMedico.nombre() != null && !datosActualizacionMedico.nombre().isEmpty()){
            this.nombre = datosActualizacionMedico.nombre();
        };
        if (datosActualizacionMedico.telefono() != null && !datosActualizacionMedico.telefono().isEmpty()) {
            this.telefono = datosActualizacionMedico.telefono();
        }
        if (datosActualizacionMedico.direccion() != null) {
            this.direccion = direccion.actualizarDatos(datosActualizacionMedico.direccion());
        }
    }

    public void desactivarMedico() {
        this.activo = false;
    }

}
