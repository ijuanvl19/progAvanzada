package co.edu.uniquindio.clinica.dto;

import co.edu.uniquindio.clinica.modelo.enums.Especialidad;
import co.edu.uniquindio.clinica.modelo.enums.EstadoCita;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ItemCitaDTO() {

    @NotEmpty
    int codigoCita,
    @NotNull
    String nombreMedico,
    @NotNull
    Especialidad especialidad,
    @NotNull
    EstadoCita estadoCita,
    @NotNull
    LocalDateTime fecha

}
