package co.edu.uniquindio.clinica.dto.administrador;

import co.edu.uniquindio.clinica.modelo.enums.Especialidad;

public record ItemMedicoDTO(int codigo,
                            String cedula,
                            String nombre,
                            String urlFoto,
                            Especialidad especialidad) {
}
