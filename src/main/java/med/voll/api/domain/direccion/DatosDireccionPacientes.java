package med.voll.api.domain.direccion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosDireccionPacientes(
        @NotBlank
        String calle,
        @NotBlank
        String distrito,
        @NotBlank
        String ciudad,
        @NotNull
        Integer numero,
        @NotBlank
        String complemento,
        @NotBlank
        String urbanizacion,
        @NotBlank
        @Pattern(regexp = "\\d{9}")
        String zip,
        @NotBlank
        String provincia)
        {
}
