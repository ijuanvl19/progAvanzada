package co.edu.uniquindio.clinica.modelo.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Table(name="horarioMedico")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HorarioMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(name = "dia")
    private LocalDateTime dia;

    @Column(name = "hora_inicio")
    private LocalDateTime horaInicio;

    @Column(name = "hora_salida")
    private LocalDateTime horaSalida;

    @Column(name = "id_medico")
    private Medico medico;
}
