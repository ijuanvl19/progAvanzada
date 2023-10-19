package co.edu.uniquindio.clinica.dto.medico;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ItemCitaDTO(

        @NotEmpty
        int codigo,
        @NotNull
        String cedulaPaciente,
        @NotNull
        String nombrePaciente,
        @NotNull
        String motivo,
        @NotNull
        LocalDateTime fecha
) {
}
