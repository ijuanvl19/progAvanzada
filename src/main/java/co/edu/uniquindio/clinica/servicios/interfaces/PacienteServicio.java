package co.edu.uniquindio.clinica.servicios.interfaces;

import co.edu.uniquindio.clinica.dto.NewPasswordDTO;
import co.edu.uniquindio.clinica.dto.medico.HorarioDTO;
import co.edu.uniquindio.clinica.dto.paciente.DetallePacienteDTO;
import co.edu.uniquindio.clinica.dto.paciente.RegistroPacienteDTO;
import co.edu.uniquindio.clinica.dto.paciente.RegistroPqrsDTO;
import co.edu.uniquindio.clinica.modelo.entidades.DiaLibreMedico;
import org.springframework.stereotype.Service;


@Service
public interface PacienteServicio {

    int registrarse(RegistroPacienteDTO pacienteDTO) throws Exception;

    int editarPerfil(DetallePacienteDTO pacienteDTO) throws Exception;

    void eliminarCuenta() throws Exception;

    void eliminarCuenta(int id) throws Exception;

    void enviarLinkRecuperacion() throws Exception;

    void agendarCita() throws Exception;

    void changePassword(String passwordActualDTO, String passwordNewDTO,
                        NewPasswordDTO pacienteDTO) throws Exception;

    void agendarCita(DiaLibreMedico diaDTO, HorarioDTO horarioDTO,
                     DetallePacienteDTO pacienteDTO) throws Exception;

    int crearPQRS(RegistroPqrsDTO pqrsDTO)throws Exception;

    void listarPQRSPaciente() throws Exception;

    void responderPQRS() throws Exception;

    void listarCitasPaciente() throws Exception;

    void filtrarCitasPorFecha() throws Exception;

    void filtrarCitasPorMedico() throws Exception;

    void verDetalleCita() throws Exception;
}
