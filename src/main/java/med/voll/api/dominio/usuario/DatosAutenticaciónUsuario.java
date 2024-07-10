package med.voll.api.dominio.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticaciónUsuario(String login, String clave) {
}
