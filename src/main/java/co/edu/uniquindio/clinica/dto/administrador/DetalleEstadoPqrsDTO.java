package co.edu.uniquindio.clinica.dto.administrador;

import co.edu.uniquindio.clinica.modelo.enums.EstadoPqrs;
import jakarta.validation.constraints.NotEmpty;

public record DetalleEstadoPqrsDTO(

        @NotEmpty
        EstadoPqrs estadoPqrs
) {
}
