package co.edu.uniquindio.clinica.dto;

import co.edu.uniquindio.clinica.modelo.enums.Especialidad;
import co.edu.uniquindio.clinica.modelo.enums.EstadoPQRS;
import java.time.LocalDateTime;
import java.util.List;

public record DetallePqrsDTO(
        int codigo,
        EstadoPQRS estado,
        String motivoPQRS,
        String nombrePaciente,
        String nombreMedico,
        Especialidad especialidad,
        LocalDateTime fecha,
        List<RespuestaDTO> mensajes

) {
}
