package co.edu.uniquindio.clinica.dto.administrador;

import co.edu.uniquindio.clinica.modelo.enums.Ciudad;
import co.edu.uniquindio.clinica.modelo.enums.Especialidad;
import java.util.List;


public record DetalleMedicoDTO(
        int codigo,
        String nombre,
        String cedula,
        Ciudad ciudad,
        Especialidad especialidad,
        String telefono,
        String correo,
        String urlFoto,
        List<HorarioDTO> horarios
) {
}