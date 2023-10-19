package co.edu.uniquindio.clinica.servicios.implementacion;


import co.edu.uniquindio.clinica.dto.administrador.DetalleMedicoDTO;
import co.edu.uniquindio.clinica.dto.medico.ItemCitaDTO;
import co.edu.uniquindio.clinica.dto.medico.RegistroAtencionDTO;
import co.edu.uniquindio.clinica.dto.paciente.DetallePacienteDTO;
import co.edu.uniquindio.clinica.modelo.entidades.Atencion;
import co.edu.uniquindio.clinica.modelo.entidades.Cita;
import co.edu.uniquindio.clinica.modelo.enums.EstadoCita;
import co.edu.uniquindio.clinica.repositorios.*;
import co.edu.uniquindio.clinica.servicios.interfaces.MedicoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoServicioImpl implements MedicoServicio {

    private final MedicoRepo medicoRepo;
    private final PqrsRepo pqrsRepo;
    private final CuentaRepo cuentaRepo;
    private final MensajeRepo mensajeRepo;
    private final CitaRepo citaRepo;
    private final HorarioRepo horarioRepo;
    private final AtencionRepo atencionRepo;

    @Override
    public List<ItemCitaDTO> listarCitasPendientes(DetalleMedicoDTO medicoDTO) throws Exception {

        List<Cita> citas = citaRepo.findByMedico_Id(medicoDTO.codigo());

        if (citas.isEmpty()) {
            throw new Exception("No tiene citas");
        }

        List<ItemCitaDTO> citasPendientes = new ArrayList<>();

        for (Cita c : citas) {
            if (c.getEstadoCita().equals(EstadoCita.PROGRAMADA)) {
                citasPendientes.add(new ItemCitaDTO(
                        c.getCodigo(),
                        c.getPaciente().getCedula(),
                        c.getPaciente().getNombre(),
                        c.getMotivo(),
                        c.getFechaCita()
                ));
            }
        }
        return citasPendientes;
    }

    @Override
    public List<ItemCitaDTO> listarCitasPendientesDia(DetalleMedicoDTO medicoDTO) {

        LocalDateTime fechaAhora = LocalDateTime.now();
        List<Cita> citas = citaRepo.findByMedico_Id(medicoDTO.codigo());

        if (citas.isEmpty()) {
            throw new Exception("No tiene citas");
        }

        List<ItemCitaDTO> citasPendientes = new ArrayList<>();

        for (Cita c : citas) {
            if (c.getFechaCita().equals(fechaAhora)) {
                citasPendientes.add(new ItemCitaDTO(
                        c.getCodigo(),
                        c.getPaciente().getCedula(),
                        c.getPaciente().getNombre(),
                        c.getMotivo(),
                        c.getFechaCita()
                ));
            }
        }
        return citasPendientes;
    }

    @Override
    public RegistroAtencionDTO atenderCita(DetallePacienteDTO pacienteDTO, DetalleMedicoDTO medicoDTO) throws Exception{

        List<Cita> citas = citaRepo.findByPacienteCedula(pacienteDTO.cedula());
        List<RegistroAtencionDTO> atencion = new ArrayList<>();

        if (citas.isEmpty()) {
             throw new Exception("No existe citas agendadas ");
        }
        for (Cita c : citas) {

            if (c.getFechaCita().equals(LocalDateTime.now()) && c.getMedico().getId() == medicoDTO.codigo()) {
                atencion.add(new RegistroAtencionDTO(
                        c.
                ));
            }
        }

    }

    public List<Atencion> listarAtencionesPaciente(DetalleMedicoDTO pacienteDTO) throws Exception {

        List<Atencion> atenciones = atencionRepo.findAllByCita_Paciente_Cedula(pacienteDTO.cedula());

        if (atenciones.isEmpty()) {
            throw new Exception("El paciente no tiene historial clinico");
        }

        return atenciones;

    }

    @Override
    public void listarCitasPaciente() throws Exception {


    }

    @Override
    public void agendarDiaLibre() throws Exception{

    }

    @Override
    public void listarCitasRealizadasMedico() throws Exception{

    }
}
