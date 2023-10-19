package co.edu.uniquindio.clinica.dto.medico;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public record HorarioDTO(

        @NotEmpty
        LocalDateTime dia,
        @NotEmpty
        LocalDateTime horaInicio,
        @NotEmpty
        LocalDateTime horaSalida
) {
}
