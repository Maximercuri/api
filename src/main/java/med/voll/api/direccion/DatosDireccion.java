package med.voll.api.direccion;

import jakarta.validation.constraints.NotBlank;

public record DatosDireccion(@NotBlank
                             String calle,

                             @NotBlank
                             String numero,

                             @NotBlank
                             String complemento,

                             @NotBlank
                             String barrio,

                             @NotBlank
                             String ciudad) {
}
