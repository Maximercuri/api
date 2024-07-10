package med.voll.api.dominio.paciente;

import med.voll.api.direccion.DatosDireccion;

public record DatosRespuestaPaciente(Long id,
                                     String nombre,
                                     String email,
                                     String telefono,
                                     String documento,
                                     DatosDireccion direccion) {
}
