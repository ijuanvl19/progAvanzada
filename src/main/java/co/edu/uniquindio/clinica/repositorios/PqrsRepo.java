package co.edu.uniquindio.clinica.repositorios;

import co.edu.uniquindio.clinica.modelo.entidades.Mensaje;
import co.edu.uniquindio.clinica.modelo.entidades.Pqrs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PqrsRepo extends JpaRepository<Pqrs, Integer> {

    Pqrs findByCodigo(int codigo);

    //Pqrs findAllById(int id);

    Pqrs findBy(int id);

    //Pqrs findByCodigo(int codigo);

    List<Pqrs> findAllById(int id);

}
