package med.voll.api.dominio.medico;

public record ListadoDeDatosMedicos(String nombre,
                                    String especialidad,
                                    String documento,
                                    String email) {

    public ListadoDeDatosMedicos(Medico medico){
        this(medico.getNombre(), medico.getEspecialidad().toString(), medico.getDocumento(), medico.getEmail());
    }

}
