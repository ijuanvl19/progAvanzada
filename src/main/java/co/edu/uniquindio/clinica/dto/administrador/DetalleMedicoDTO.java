package co.edu.uniquindio.clinica.dto.administrador;

import co.edu.uniquindio.clinica.dto.medico.HorarioDTO;
import co.edu.uniquindio.clinica.modelo.entidades.DiaLibreMedico;
import co.edu.uniquindio.clinica.modelo.enums.Ciudad;
import co.edu.uniquindio.clinica.modelo.enums.Especialidad;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;


public record DetalleMedicoDTO(
        @Positive
        int codigo,
        @NotEmpty
        @Length(max = 200)
        String nombre,
        @NotEmpty
        @Length(max = 10)
        String cedula,
        @NotNull
        Ciudad ciudad,
        @NotNull
        Especialidad especialidad,
        @NotEmpty
        @Length(max = 20)
        String telefono,
        @NotEmpty
        @Email
        @Length(max = 80)
        String correo,
        @NotEmpty
        String urlFoto,
        @NotEmpty
        DiaLibreMedico diaLibre,
        @NotEmpty
        List<HorarioDTO> horarios
) {
}