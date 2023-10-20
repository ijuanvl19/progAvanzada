package co.edu.uniquindio.clinica.servicios.interfaces;

import co.edu.uniquindio.clinica.dto.administrador.DetalleMedicoDTO;
import co.edu.uniquindio.clinica.dto.medico.DiaLibreDTO;
import co.edu.uniquindio.clinica.dto.medico.ItemCitaDTO;
import co.edu.uniquindio.clinica.dto.medico.RegistroAtencionDTO;
import co.edu.uniquindio.clinica.dto.paciente.DetallePacienteDTO;
import co.edu.uniquindio.clinica.modelo.entidades.Atencion;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface MedicoServicio {

    List<ItemCitaDTO> listarCitasPendientes(DetalleMedicoDTO medicoDTO) throws Exception;

    List<ItemCitaDTO> listarCitasPendientesDia(DetalleMedicoDTO medicoDTO) throws Exception;

    void atenderCita(DetallePacienteDTO pacienteDTO, DetalleMedicoDTO medicoDTO,
                                    RegistroAtencionDTO atencionDTO) throws Exception;

    List<Atencion> listarAtencionesPaciente(DetallePacienteDTO pacienteDTO) throws Exception;


    LocalDateTime agendarDiaLibre(DetalleMedicoDTO medicoDTO,
                                  DiaLibreDTO fechaLibreDTO) throws Exception;

    List<ItemCitaDTO> listarCitasRealizadasMedico(DetalleMedicoDTO medicoDTO) throws Exception;
}
