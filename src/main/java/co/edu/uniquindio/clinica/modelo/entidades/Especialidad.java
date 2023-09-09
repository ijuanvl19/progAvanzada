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
public class Especialidad implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String idEspecialidad;
    private String Nombre;
    private String TipoTitulo;
    //private String medico; fk a medico
}