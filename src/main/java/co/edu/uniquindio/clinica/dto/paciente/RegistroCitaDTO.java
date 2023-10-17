package co.edu.uniquindio.clinica.dto.paciente;

import co.edu.uniquindio.clinica.modelo.entidades.Medico;
import co.edu.uniquindio.clinica.modelo.enums.EstadoCita;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public record RegistroCitaDTO(

        @NotEmpty
        LocalDateTime fechaCreacion,
        @NotEmpty
        LocalDateTime fechaCita,
        @NotEmpty
        String motivo,
        @NotEmpty
        String cedulaPaciente,
        @NotEmpty
        String nombrePaciente,
        @NotEmpty
        String nombreMedico,
        @NotEmpty
        int idMedico,
        @NotEmpty
        EstadoCita estadoCita

) {
}
