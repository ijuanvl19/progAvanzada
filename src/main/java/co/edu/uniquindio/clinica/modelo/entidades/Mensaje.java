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
@Table(name="mensaje")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Mensaje implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "mensaje", length = 200)
    private String Mensaje;

    @Column(name = "id_pqrs")
    private Pqrs pqrs;

    @Column(name = "id_cuenta")
    private Cuenta cuenta;

    @Column(name = "fecha_mensaje")
    private LocalDateTime fechaMensaje;

     //private String administrador;
    //private String pqrs;      fks
   // private String mensaje;
}