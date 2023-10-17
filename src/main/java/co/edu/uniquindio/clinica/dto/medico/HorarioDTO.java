package co.edu.uniquindio.clinica.dto.medico;

import co.edu.uniquindio.clinica.modelo.entidades.Medico;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public record HorarioDTO(

        @NotEmpty
        String dia,
        @NotEmpty
        LocalDateTime horaInicio,
        @NotEmpty
        LocalDateTime horaSalida
) {
}
