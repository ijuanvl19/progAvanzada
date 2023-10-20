package co.edu.uniquindio.clinica.servicios.implementacion;


import co.edu.uniquindio.clinica.dto.administrador.DetalleMedicoDTO;
import co.edu.uniquindio.clinica.dto.medico.DiaLibreDTO;
import co.edu.uniquindio.clinica.dto.medico.ItemCitaDTO;
import co.edu.uniquindio.clinica.dto.medico.RegistroAtencionDTO;
import co.edu.uniquindio.clinica.dto.paciente.DetallePacienteDTO;
import co.edu.uniquindio.clinica.modelo.entidades.Atencion;
import co.edu.uniquindio.clinica.modelo.entidades.Cita;
import co.edu.uniquindio.clinica.modelo.entidades.DiaLibreMedico;
import co.edu.uniquindio.clinica.modelo.enums.EstadoCita;
import co.edu.uniquindio.clinica.repositorios.*;
import co.edu.uniquindio.clinica.servicios.interfaces.MedicoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoServicioImpl implements MedicoServicio {

    private final CitaRepo citaRepo;
    private final AtencionRepo atencionRepo;
    private final DiaLibreRepo diaLibreRepo;

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
    public List<ItemCitaDTO> listarCitasPendientesDia(DetalleMedicoDTO medicoDTO) throws Exception {

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
    public void atenderCita(DetallePacienteDTO pacienteDTO, DetalleMedicoDTO medicoDTO,
                                           RegistroAtencionDTO atencionDTO) throws Exception{

        List<Cita> citas = citaRepo.findByPacienteCedula(pacienteDTO.cedula());

        if (citas.isEmpty()) {
             throw new Exception("No existe citas agendadas ");
        }
        for (Cita c : citas) {

            if (c.getFechaCita().equals(LocalDateTime.now()) && c.getMedico().getId() == medicoDTO.codigo()) {

                Atencion atencion = new Atencion();
                atencion.setDiagnostico(atencionDTO.diagnostico());
                atencion.setTratamiento(atencionDTO.tratamiento());
                atencion.setNotasMedicas(atencionDTO.notasMedicas());
                atencion.setCita(c);
                atencionRepo.save(atencion);
            }

        }
    }

    public List<Atencion> listarAtencionesPaciente(DetallePacienteDTO pacienteDTO) throws Exception {

        List<Atencion> atenciones = atencionRepo.findAllByCita_Paciente_Cedula(pacienteDTO.cedula());

        if (atenciones.isEmpty()) {
            throw new Exception("El paciente no tiene historial clinico");
        }

        return atenciones;

    }

    @Override
    public LocalDateTime agendarDiaLibre(DetalleMedicoDTO medicoDTO, DiaLibreDTO fechaLibreDTO) throws Exception{

        LocalDateTime fechaSistema = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy MMMM dd, HH:mm");
        String fecha = formato.format(fechaLibreDTO.fechaLibre());

        if (fechaLibreDTO.fechaLibre().isBefore(fechaSistema)) {
            throw new Exception("Fecha invalida");
        }
        List<Cita> citas = citaRepo.findByMedico_Id(medicoDTO.codigo());

        if (citas.isEmpty()) {
            throw new Exception("No tiene citas agendadas");
        }
        for (Cita c : citas) {

            String fechaCita = formato.format(c.getFechaCita());
            if (fecha.equals(fechaCita)) {
                throw new Exception("Tiene citas agendadas en esa fecha");
            }else {
                DiaLibreMedico diaLibreMedico = new DiaLibreMedico();
                diaLibreMedico.setFechaLibre(fechaLibreDTO.fechaLibre());
                diaLibreRepo.save(diaLibreMedico);
            }
        }
        return fechaLibreDTO.fechaLibre();
    }

    @Override
    public List<ItemCitaDTO> listarCitasRealizadasMedico(DetalleMedicoDTO medicoDTO) throws Exception{

        List<Cita> citas = citaRepo.findAllById(medicoDTO.codigo());

        if (citas.isEmpty()) {
            throw new Exception("No hay citas que mostrar");
        }

        List<ItemCitaDTO> lista = new ArrayList<>();

        for (Cita c : citas) {

            lista.add(new ItemCitaDTO(
                    c.getCodigo(),
                    c.getPaciente().getCedula(),
                    c.getPaciente().getNombre(),
                    c.getMotivo(),
                    c.getFechaCita()
            ));
        }
        return lista;
    }
}
