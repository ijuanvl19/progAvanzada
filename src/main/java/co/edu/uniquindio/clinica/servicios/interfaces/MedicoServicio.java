package co.edu.uniquindio.clinica.servicios.interfaces;

import co.edu.uniquindio.clinica.dto.administrador.DetalleMedicoDTO;
import co.edu.uniquindio.clinica.dto.medico.ItemCitaDTO;
import co.edu.uniquindio.clinica.dto.medico.RegistroAtencionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicoServicio {

    List<ItemCitaDTO> listarCitasPendientes(DetalleMedicoDTO medicoDTO) throws Exception;

    List<ItemCitaDTO> listarCitasPendientesDia(DetalleMedicoDTO medicoDTO) throws Exception;

    RegistroAtencionDTO atenderCita() throws Exception;

    void listarAtencionesPaciente() throws Exception;

    void listarCitasPaciente() throws Exception; //historial médico

    void agendarDiaLibre() throws Exception;


    void listarCitasRealizadasMedico() throws Exception;
}
