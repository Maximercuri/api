package med.voll.api.dominio.paciente;

public record ListadoDeDatosPacientes(String nombre,
                                      String email,
                                      String documento,
                                      String telefono) {

    public ListadoDeDatosPacientes(Paciente paciente){
        this(paciente.getNombre(), paciente.getEmail(), paciente.getDocumento(), paciente.getTelefono());
    }

}
