package co.edu.uniquindio.clinica.dto.administrador;

import co.edu.uniquindio.clinica.modelo.enums.EstadoPqrs;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record RegistroRespuestaDTO(
        @Positive
        int codigoCuenta,
        @Positive
        int codigoPqrs,
        @Positive
        int codigoMensaje,
        @NotEmpty
        EstadoPqrs estadoPqrs,
        @NotBlank
        String mensaje
) {
}
