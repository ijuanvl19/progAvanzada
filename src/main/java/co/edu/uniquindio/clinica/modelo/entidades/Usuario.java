package co.edu.uniquindio.clinica.modelo.entidades;


import jakarta.persistence.Column;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;


import java.io.Serializable;


@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario extends Cuenta implements Serializable {

    @Column(nullable = false, length = 10, unique = true)
    private String cedula;

    @Column(nullable = false, length = 200)
    private String nombre;

    @Column(nullable = false, length = 20)
    private String telefono;

    @Column(nullable = false)
    private urlFoto;

    @Column(nullable = false)
    private Ciudad ciudad;

    @Column(nullable = false)
    private estadoUsuario estado;
}
