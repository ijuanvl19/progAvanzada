package co.edu.uniquindio.clinica.dto.paciente;

import co.edu.uniquindio.clinica.modelo.enums.Especialidad;
import co.edu.uniquindio.clinica.modelo.enums.EstadoCita;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ItemCitaDTO(

        @NotEmpty
        int codigoCita,
        @NotNull
        LocalDateTime fecha,
        @NotNull
        EstadoCita estadoCita,
        @NotNull
        String nombreMedico,
        @NotNull
        Especialidad especialidad

) {
}
