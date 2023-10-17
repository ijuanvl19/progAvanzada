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
    private int id;

    @Column(name="fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name="fecha_cita")
    private LocalDateTime fechaCita;

    @Column(name="motivo")
    private String motivo;

    @Column(name = "nombre_paciente",nullable = false)
    private String NombrePaciente;

    @Column(name = "nombre_medico",nullable = false)
    private String nombreMedico;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_cita")
    private EstadoCita estadoCita;

}


