package co.edu.uniquindio.clinica.dto.administrador;

import co.edu.uniquindio.clinica.modelo.enums.Especialidad;
import co.edu.uniquindio.clinica.modelo.enums.EstadoPqrs;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record DetallePqrsDTO(
        @NotEmpty
        int codigo,
        @NotNull
        EstadoPqrs estado,
        @NotNull
        String motivoPQRS,
        @NotNull
        String nombrePaciente,
        @NotNull
        String nombreMedico,
        @NotNull
        Especialidad especialidad,
        @NotNull
        LocalDateTime fecha,

        List<RespuestaDTO> mensajes

) {
}
