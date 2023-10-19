package co.edu.uniquindio.clinica.dto.administrador;

import co.edu.uniquindio.clinica.modelo.enums.Especialidad;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ItemMedicoDTO(
        @NotEmpty
        int codigo,
        @NotNull
        String cedula,
        @NotNull
        String nombre,
        @NotNull
        String urlFoto,
        @NotNull
        Especialidad especialidad
) {
}
