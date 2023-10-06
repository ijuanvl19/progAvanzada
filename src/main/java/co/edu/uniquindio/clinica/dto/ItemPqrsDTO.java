package co.edu.uniquindio.clinica.dto;

import co.edu.uniquindio.clinica.modelo.enums.EstadoPqrs;
import java.time.LocalDateTime;

public record ItemPqrsDTO(int codigo,
                          EstadoPqrs estado,
                          String motivo,
                          LocalDateTime fecha,
                          String nombrePaciente) {
}
