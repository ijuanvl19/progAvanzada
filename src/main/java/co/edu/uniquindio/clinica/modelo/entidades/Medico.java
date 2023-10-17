package co.edu.uniquindio.clinica.modelo.entidades;


import co.edu.uniquindio.clinica.modelo.enums.Especialidad;
import jakarta.persistence.*;

import java.io.Serializable;

import lombok.*;

@Entity
@Table(name="medico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Medico extends Usuario implements Serializable {

    @Enumerated(EnumType.STRING)
    @Column(name = "especialidad")
    private Especialidad especialidad;

    @Column(name = "horario_medico")
    private HorarioMedico horarioMedico;

}