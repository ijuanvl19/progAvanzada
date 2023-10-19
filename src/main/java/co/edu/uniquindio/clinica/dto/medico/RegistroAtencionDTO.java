package co.edu.uniquindio.clinica.dto.medico;

import co.edu.uniquindio.clinica.modelo.entidades.Atencion;
import co.edu.uniquindio.clinica.modelo.entidades.Cita;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RegistroAtencionDTO(

        @NotNull
        String diagnostico,
        @NotNull
        String tratamiento,
        @NotNull
        String notasMedicas,
        @NotNull
        Cita cita

) {
}
