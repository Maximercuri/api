package med.voll.api.infra.errores;

public class ExcepcionDeValidacionDeIntegridad extends RuntimeException {

    public ExcepcionDeValidacionDeIntegridad(String s){
        super(s);
    }

}
