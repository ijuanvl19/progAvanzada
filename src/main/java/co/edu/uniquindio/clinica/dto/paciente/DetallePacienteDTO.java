package co.edu.uniquindio.clinica.dto.paciente;

import co.edu.uniquindio.clinica.modelo.enums.Ciudad;
import co.edu.uniquindio.clinica.modelo.enums.TipoSangre;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public record DetallePacienteDTO(
        @Positive
        int id,
        @NotEmpty
        @Length(max = 200)
        String name,
        @NotEmpty
        @Length(max = 10)
        String cedula,
        @NotNull
        Ciudad ciudad,
        @NotEmpty
        @Length(max = 20)
        String phone,
        @NotEmpty
        @Email
        @Length(max = 80)
        String email,
        @NotEmpty
        String urlImage,
        @NotEmpty
        TipoSangre tipoSangre
) {
}
