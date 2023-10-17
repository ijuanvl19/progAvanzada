package co.edu.uniquindio.clinica.dto.administrador;

import co.edu.uniquindio.clinica.modelo.enums.TipoSangre;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.List;

public record HistorialCitaMedico(

        String cedula,
        String nombre,
        String urlFoto,
        LocalDateTime fechaNacimiento,
        String alergias,
        TipoSangre tipoSangre

) {
}
