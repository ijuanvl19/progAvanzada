package co.edu.uniquindio.clinica.dto.paciente;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public record RespuestaPacienteDTO(

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
