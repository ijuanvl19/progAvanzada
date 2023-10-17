package co.edu.uniquindio.clinica.dto.administrador;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record RegistroRespuestaDTO(
        @Positive
        int id,
        @Positive
        int codigoPQRS,
        @Positive
        int codigoMensaje,
        @NotBlank
        String mensaje
) {
}
