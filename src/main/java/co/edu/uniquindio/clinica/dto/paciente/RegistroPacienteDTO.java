package co.edu.uniquindio.clinica.dto.paciente;

import co.edu.uniquindio.clinica.modelo.enums.Ciudad;
import co.edu.uniquindio.clinica.modelo.enums.Eps;
import co.edu.uniquindio.clinica.modelo.enums.TipoSangre;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record RegistroPacienteDTO(
        @NotBlank
        @Length(max = 10)
        String dni,
        @NotBlank
        @Length(max = 200)
        String name,
        @NotBlank
        @Length(max = 20)
        String phone,
        @NotBlank
        String urlImage,
        @NotNull
        Ciudad ciudad,
        @NotBlank
        @Email
        @Length(max = 80)
        String email,
        @NotBlank
        @Length(max = 10, min = 10)
        String password,
        @NotBlank
        LocalDateTime birthdate,
        @NotBlank
        @Length(max = 100)
        String allergies,
        Eps eps,
        @NotBlank
        TipoSangre tipoSangre



) {
}
