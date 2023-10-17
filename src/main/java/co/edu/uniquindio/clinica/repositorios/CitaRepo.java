package co.edu.uniquindio.clinica.repositorios;

import co.edu.uniquindio.clinica.modelo.entidades.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CitaRepo extends JpaRepository<Cita, Integer> {

    Cita findByPacienteCedula(String cedula);

    Cita findAllByPaciente(String cedula);

    Cita findCitaByMedicoId(int id);

    Cita findCitasByMedico(String cedula);



}
