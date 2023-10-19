package co.edu.uniquindio.clinica.dto;

import co.edu.uniquindio.clinica.modelo.entidades.Medico;
import co.edu.uniquindio.clinica.modelo.entidades.Paciente;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

public record CitaDTO(

        @NotEmpty
        String id,
        @NotEmpty
        LocalDateTime fechaCreacion,
        @NotEmpty
        LocalDateTime fechaCita,
        @NotEmpty
        @NotNull
        Medico medico,
        @NotEmpty
        String motivo,
        @NotNull
        Enum EstadoCita

) {
}
