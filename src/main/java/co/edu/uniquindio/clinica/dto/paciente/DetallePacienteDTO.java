package co.edu.uniquindio.clinica.dto.paciente;

import co.edu.uniquindio.clinica.modelo.enums.Ciudad;
import co.edu.uniquindio.clinica.modelo.enums.Eps;
import co.edu.uniquindio.clinica.modelo.enums.TipoSangre;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record DetallePacienteDTO(
        @Positive
        int id,
        @NotEmpty
        @Length(max = 200)
        String nombre,
        @NotEmpty
        @Length(max = 10)
        String cedula,
        @NotNull
        Ciudad ciudad,
        @NotEmpty
        @Length(max = 20)
        String telefono,
        @NotEmpty
        @Email
        @Length(max = 80)
        String correo,
        @NotEmpty
        LocalDateTime fechaNacimiento,
        @Length(max = 225)
        String alergias,
        @NotEmpty
        TipoSangre tipoSangre,
        @NotEmpty
        Eps eps,
        @NotEmpty
        String urlFoto
) {
}
