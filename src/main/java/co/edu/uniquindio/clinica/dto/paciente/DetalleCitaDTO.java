package co.edu.uniquindio.clinica.dto.paciente;

import co.edu.uniquindio.clinica.modelo.entidades.Cita;
import co.edu.uniquindio.clinica.modelo.entidades.Medico;
import co.edu.uniquindio.clinica.modelo.entidades.Paciente;
import co.edu.uniquindio.clinica.modelo.enums.Especialidad;
import co.edu.uniquindio.clinica.modelo.enums.EstadoCita;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DetalleCitaDTO(

        @NotEmpty
        @NotNull
        @NotBlank
        int id,
        @NotEmpty
        LocalDateTime fechaCita,
        @NotEmpty
        String motivo,
        @NotNull
        EstadoCita EstadoCita,
        @NotEmpty
        @NotNull
        String nombreMedico,
        @NotNull
        Especialidad especialidad
) {
}
