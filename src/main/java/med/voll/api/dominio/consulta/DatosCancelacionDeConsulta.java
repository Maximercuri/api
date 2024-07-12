package med.voll.api.dominio.consulta;

import jakarta.validation.constraints.NotBlank;

public record DatosCancelacionDeConsulta(@NotBlank Long idConsulta,
                                         @NotBlank MotivoCancelacion motivo) {
}
