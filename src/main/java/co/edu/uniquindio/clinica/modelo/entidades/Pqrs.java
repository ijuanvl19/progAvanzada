package co.edu.uniquindio.clinica.modelo.entidades;


import co.edu.uniquindio.clinica.modelo.enums.EstadoPqrs;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import lombok.*;

@Entity
@Table(name="pqrs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pqrs implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    private int codigo;

    @Column(name = "fecha_creacion")
    private LocalDateTime FechaCreacion;

    @Column(name = "motivo",length = 200)
    private String motivo;

    @Column(name = "id_cita")
    private Cita cita;

    @Column(name = "paciente")
    private Paciente paciente;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_pqrs")
    private EstadoPqrs estadoPqrs;

    @Column(name = "mensaje")
    private Mensaje mensajes;


    //private String Paciente;
    //private String Estado;      fks

}