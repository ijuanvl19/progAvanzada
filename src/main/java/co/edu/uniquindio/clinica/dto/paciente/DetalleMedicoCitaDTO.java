package co.edu.uniquindio.clinica.dto.paciente;

import co.edu.uniquindio.clinica.dto.medico.HorarioDTO;
import co.edu.uniquindio.clinica.modelo.enums.Ciudad;
import co.edu.uniquindio.clinica.modelo.enums.Especialidad;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

public record DetalleMedicoCitaDTO(

        @Positive
        int id,
        @NotEmpty
        @Length(max = 200)
        String nombre,
        @NotNull
        Especialidad especialidad,
        @NotEmpty
        String urlFoto,
        @NotEmpty
        LocalDateTime diaLibre,
        @NotEmpty
        List<HorarioDTO> horarios
) {
}
