package co.edu.uniquindio.clinica.modelo.entidades;

import co.edu.uniquindio.clinica.modelo.enums.EstadoCita;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import lombok.*;

@Entity
@Table(name="cita")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cita implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(name="fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name="fecha_cita")
    private LocalDateTime fechaCita;

    @Column(name="motivo")
    private String motivo;

    @Column(name = "paciente",nullable = false)
    private Paciente paciente;

    @Column(name = "medico",nullable = false)
    private Medico medico;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_cita")
    private EstadoCita estadoCita;

}


