package co.edu.uniquindio.clinica.modelo.entidades;


import co.edu.uniquindio.clinica.modelo.enums.Eps;
import co.edu.uniquindio.clinica.modelo.enums.TipoSangre;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;


@Table(name="paciente")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Paciente extends Usuario implements Serializable {


    @Column(name = "fechaNacimiento")
    private LocalDateTime FechaNacimiento;

    @Column(name = "alergias")
    private String Alergias;

    @Enumerated(EnumType.STRING)
    @Column(name = "eps")
    private Eps eps;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_sangre")
    private TipoSangre tipoSangre;

}