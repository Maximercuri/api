package med.voll.api.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class TratamientoDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratamientoError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratamientoError400(MethodArgumentNotValidException error) {
        var errores = error.getFieldErrors().stream()
                .map(DatosDeErrorValidacion::new)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ExcepcionDeValidacionDeIntegridad.class)
    public ResponseEntity tratamientoErrorDeValidacionDeIntegridad(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity tratamientoErrorDeValidacion(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    public record DatosDeErrorValidacion(String campoErroneo, String error) {
        public DatosDeErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
