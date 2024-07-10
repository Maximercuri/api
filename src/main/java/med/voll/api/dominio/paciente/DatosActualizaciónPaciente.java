package med.voll.api.dominio.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.direccion.DatosDireccion;

public record DatosActualizaciónPaciente(@NotNull
                                         Long id,
                                         String nombre,
                                         String telefono,
                                         String email,
                                         DatosDireccion direccion) {
}
