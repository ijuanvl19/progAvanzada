package co.edu.uniquindio.clinica.repositorios;

import co.edu.uniquindio.clinica.modelo.entidades.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CitaRepo extends JpaRepository<Cita, Integer> {

    //Cita findByPacienteCedula(String cedula);

    List<Cita> findByPacienteCedula(String cedula);

    List<Cita> findAllById(int id);

    List<Cita> findAllByFechaCita(LocalDateTime fecha);

    List<Cita> findAllByMedicoNombre(String nombre);

}
