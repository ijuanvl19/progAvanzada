package co.edu.uniquindio.clinica.dto.administrador;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RespuestaDTO (
        @NotEmpty
        int codigoMensaje,
        @NotEmpty
        String mensaje,
        @NotEmpty
        String nombreUsuario,
        @NotEmpty
        LocalDateTime fecha
) {
}
