package co.edu.uniquindio.clinica.servicios.interfaces;

import co.edu.uniquindio.clinica.dto.NewPasswordDTO;
import co.edu.uniquindio.clinica.dto.administrador.RegistroRespuestaDTO;
import co.edu.uniquindio.clinica.dto.medico.HorarioDTO;
import co.edu.uniquindio.clinica.dto.paciente.*;
import co.edu.uniquindio.clinica.modelo.entidades.DiaLibreMedico;
import co.edu.uniquindio.clinica.modelo.enums.Especialidad;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public interface PacienteServicio {

    int registrarse(RegistroPacienteDTO pacienteDTO) throws Exception;

    int editarPerfil(DetallePacienteDTO pacienteDTO) throws Exception;

    DetallePacienteDTO verDetallePaciente(DetallePacienteDTO pacienteDTO) throws Exception;

    void eliminarCuenta(int id) throws Exception;

    void enviarLinkRecuperacion() throws Exception;

    void cambiarPassword(String passwordActualDTO, String passwordNewDTO,
                        NewPasswordDTO pacienteDTO) throws Exception;


    void crearCita(DetalleMedicoCitaDTO medicoDTO, RegistroCitaDTO citaDTO,
                     DetallePacienteDTO pacienteDTO, LocalDateTime fechaCita,
                     Especialidad especialidad) throws Exception;

    int modificarCita(DetalleCitaDTO citaDTO) throws Exception;

    void cancelarCita(DetalleCitaDTO citaDTO) throws Exception;

    void crearPqrs(RegistroPqrsDTO pqrsDTO, DetallePacienteDTO pacienteDTO) throws Exception;

    List<ItemPqrsPacienteDTO> listarPqrsPaciente(int id) throws Exception;

    int responderPqrsPaciente(RegistroRespuestaPacienteDTO registroRespuestaPacienteDTO) throws Exception;

    List<DetalleCitaDTO> listarCitasPaciente(int id) throws Exception;

    List<ItemCitaDTO> filtrarCitasPorFecha(LocalDateTime fechaCita) throws Exception;

    List<ItemCitaDTO> filtrarCitasPorMedico(String nombreMedico) throws Exception;

    DetalleCitaDTO verDetalleCita(int id) throws Exception;
}
