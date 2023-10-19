package co.edu.uniquindio.clinica.modelo.entidades;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="atencion")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Atencion implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "diagnostico", length = 225)
    private String diagnostico;

    @Column(name = "tratamiento", length = 225)
    private String tratamiento;

    @Column(name = "notas_medicas", length = 225)
    private String notasMedicas;

    @Column(name = "id_cita",nullable = false)
    private Cita cita;


    //private String Cita; llave a cita

    //private String Medico; llave a medico


}


