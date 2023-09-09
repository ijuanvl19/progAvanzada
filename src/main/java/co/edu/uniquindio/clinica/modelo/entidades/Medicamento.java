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
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Medicamento implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String idMedicamento;
    private String Nombre;
    private String Dosis;
    private String entidad;
    private int valor;
    //private String Atencion; fk a formula
}