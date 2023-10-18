package co.edu.uniquindio.clinica.repositorios;

import co.edu.uniquindio.clinica.modelo.entidades.DiaLibreMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaLibreRepo extends JpaRepository<DiaLibreMedico, Integer> {

    DiaLibreMedico findByCodigo(int codigo);

   List<DiaLibreMedico> findByCodigo(int codigo);

   DiaLibreMedico findByMedicoNombre(String nombreMedico);


}
