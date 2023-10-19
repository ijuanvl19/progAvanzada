package co.edu.uniquindio.clinica.dto.paciente;

import co.edu.uniquindio.clinica.dto.administrador.RespuestaDTO;
import co.edu.uniquindio.clinica.modelo.entidades.Cita;
import co.edu.uniquindio.clinica.modelo.entidades.Mensaje;
import co.edu.uniquindio.clinica.modelo.entidades.Paciente;
import co.edu.uniquindio.clinica.modelo.enums.EstadoPqrs;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public record RegistroPqrsDTO(

        @Positive
        @NotEmpty
        int id,
        @Positive
        @NotEmpty
        String NombrePaciente,
        @NotEmpty
        LocalDateTime fechaCreacion,
        @NotEmpty
        String motivo,
        @NotEmpty
        Mensaje mensaje,
        @NotEmpty
        EstadoPqrs EstadoPqrs,

        List<RespuestaDTO> mensajes
) {
}
