package co.edu.uniquindio.clinica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record NewPasswordDTO(

        @NotEmpty
        int id,
        @NotEmpty
        String dni,
        @NotEmpty
        String name,
        @NotEmpty
        String email,
        @NotEmpty
        @NotBlank
        String password
) {
}
