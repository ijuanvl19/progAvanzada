package co.edu.uniquindio.clinica.modelo.entidades;

import jakarta.persistence.*;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;


@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Administrador extends Cuenta implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "nombre")
    private String NombreAdministrador;

}


