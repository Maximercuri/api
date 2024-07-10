package med.voll.api.dominio.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.direccion.DatosDireccion;

public record DatosActualizaciónMedico(@NotNull
                                       Long id,
                                       String nombre,
                                       String telefono,
                                       DatosDireccion direccion) {
}
