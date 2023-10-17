package co.edu.uniquindio.clinica.repositorios;

import co.edu.uniquindio.clinica.modelo.entidades.Medico;
import co.edu.uniquindio.clinica.modelo.enums.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepo extends JpaRepository<Medico, Integer> {

    Medico findByCorreo(String correo);

    Medico findByCedula(String cedula);

    Medico findAllByEspecialidad(Especialidad especialidad);

    Medico findMedicoByEspecialidad(Especialidad especialidad);
}
