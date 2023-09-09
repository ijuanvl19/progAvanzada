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
public class FacturaDetalles implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String idFacturaDt;
    private String TipoDetalle;
    private String Descripcion;
    private int valor;
    //private String Atencion; fk a factura
}