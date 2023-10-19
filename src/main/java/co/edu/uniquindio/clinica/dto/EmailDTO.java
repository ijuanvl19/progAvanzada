package co.edu.uniquindio.clinica.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record EmailDTO(

        @Email
        @NotEmpty
        String correo
) {

}
