package co.edu.uniquindio.clinica.modelo.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class Paciente extends Cuenta implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String idPaciente;
    private String Cedula;
    private String Nombre;
    private String Telefono;
    private String Ciudad;
    private LocalDateTime FechaNacimiento;
    private String Alergias;
    //private String eps;
    //private String Tipo_sangre;      fks

}