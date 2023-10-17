package co.edu.uniquindio.clinica.servicios.implementacion;

import co.edu.uniquindio.clinica.dto.NewPasswordDTO;
import co.edu.uniquindio.clinica.dto.administrador.DetalleMedicoDTO;
import co.edu.uniquindio.clinica.dto.administrador.RespuestaDTO;
import co.edu.uniquindio.clinica.dto.medico.HorarioDTO;
import co.edu.uniquindio.clinica.dto.paciente.*;
import co.edu.uniquindio.clinica.modelo.entidades.*;
import co.edu.uniquindio.clinica.modelo.enums.Especialidad;
import co.edu.uniquindio.clinica.modelo.enums.EstadoCita;
import co.edu.uniquindio.clinica.modelo.enums.EstadoPqrs;
import co.edu.uniquindio.clinica.modelo.enums.EstadoUsuario;
import co.edu.uniquindio.clinica.repositorios.*;
import co.edu.uniquindio.clinica.servicios.interfaces.PacienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PacienteServicioImpl implements PacienteServicio {

    private final MedicoRepo medicoRepo;
    private final PacienteRepo pacienteRepo;
    private final PqrsRepo pqrsRepo;
    private final CuentaRepo cuentaRepo;
    private final MensajeRepo mensajeRepo;
    private final CitaRepo citaRepo;
    private final HorarioRepo horarioRepo;

    @Override
    public int registrarse(RegistroPacienteDTO pacienteDTO) {

        if( estaRepetidaCedula(pacienteDTO.dni()) ){
            throw new Exception("La cédula " + pacienteDTO.dni() + " ya está en uso");
        }

        if( estaRepetidoCorreo(pacienteDTO.email()) ){
            throw new Exception("El correo " + pacienteDTO.dni() + " ya está en uso");
        }

        Paciente paciente = new Paciente();
        paciente.setCedula(pacienteDTO.dni());
        paciente.setTelefono(pacienteDTO.phone());
        paciente.setNombre(pacienteDTO.name());
        paciente.setUrlFoto(pacienteDTO.urlImage());
        paciente.setCiudad(pacienteDTO.ciudad());
        paciente.setFechaNacimiento(pacienteDTO.birthdate());
        paciente.setAlergias(pacienteDTO.allergies());
        paciente.setEps(pacienteDTO.eps());
        paciente.setTipoSangre(pacienteDTO.tipoSangre());
        paciente.setCorreo(pacienteDTO.email());
        paciente.setPassword(paciente.getPassword());
        paciente.setEstado(EstadoUsuario.ACTIVO);

        Paciente pacienteNuevo = pacienteRepo.save(paciente);

        return pacienteNuevo.getId();

    }

    private boolean estaRepetidaCedula(String dni) {
        return pacienteRepo.findByCedula(dni) != null;
    }

    private boolean estaRepetidoCorreo(String email) {
        return pacienteRepo.findByCorreo(email) != null;
    }

    @Override
    public int editarPerfil(DetallePacienteDTO pacienteDTO) throws Exception {

        Optional<Paciente> optional = pacienteRepo.findById(pacienteDTO.id());

        if( optional.isEmpty() ){
            throw new Exception("No existe un paciente con el código " + pacienteDTO.id());
        }

        Paciente buscado = optional.get();

        buscado.setNombre(pacienteDTO.name());
        buscado.setCedula(pacienteDTO.cedula());
        buscado.setCorreo(pacienteDTO.email());
        buscado.setCiudad(pacienteDTO.ciudad());
        buscado.setTipoSangre(pacienteDTO.tipoSangre());
        buscado.setTelefono(pacienteDTO.phone());
        buscado.setUrlFoto(pacienteDTO.urlImage());

        pacienteRepo.save(buscado);

        return buscado.getId();
    }

    @Override
    public void eliminarCuenta(int id) throws Exception {

        Optional<Paciente> optional = pacienteRepo.findById(id);

        if( optional.isEmpty() ){
            throw new Exception("No existe un médico con el código " + id);
        }
        Paciente buscado =  optional.get();
        buscado.setEstado(EstadoUsuario.INACTIVO);
        pacienteRepo.save(buscado);
    }

    @Override
    public void enviarLinkRecuperacion() throws Exception{

    }

    @Override
    public void changePassword(String passwordActualDTO, String passwordNewDTO,
                                 NewPasswordDTO pacienteDTO) throws Exception {

        String message = "";
        Optional<Paciente> optional = pacienteRepo.findById(pacienteDTO.id());

        if( optional.isEmpty() ){
            throw new Exception("No existe un paciente con el código " + pacienteDTO.id());
        }

        Paciente buscado = optional.get();

        if (buscado.getPassword().equals(passwordActualDTO) && pacienteDTO.dni().equals(buscado.getCedula())) {

            buscado.setPassword(passwordNewDTO);

            pacienteRepo.save(buscado);
            message = "La contraseña se cambio satisfactoriamente";
        }else{
            message = "La contraseña actual no coincide";
        }


    }

    @Override
    public void agendarCita(DetalleMedicoCitaDTO medicoDTO, RegistroCitaDTO citaDTO,
                            String cedula, LocalDateTime fechaInicioCitaDTO, LocalDateTime fechaFinCitaDTO,
                            Especialidad especialidad) throws Exception {

        LocalDateTime fechaSistema = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy MMMM dd, HH:mm");
        String fechaAhora = formato.format(fechaSistema);
        String fechaInicioCitaFormateada = formato.format(fechaInicioCitaDTO);
        String fechaFinCitaFormateada = formato.format(fechaFinCitaDTO);

        List<Cita> citas = (List<Cita>) citaRepo.findByPacienteCedula(cedula);




        List<HorarioDTO> horaDisponible = new ArrayList<>();

        if (fechaInicioCitaDTO.isAfter(fechaSistema)) {

            List<Medico> medicos = (List<Medico>) medicoRepo.findAllByEspecialidad(especialidad);

            if(medicos.isEmpty()){
                throw new Exception("No hay médicos registrados con esa especialidad");
            }else {



                Optional<HorarioMedico> optional =horarioRepo.findById(medicoDTO.id());





                for (Medico m : medicos){

                    if (m.getHorarioMedico().getHoraInicio().isEqual(fechaInicioCitaDTO) && citas.size() < 4
                    && fechaInicioCitaDTO.isBefore(m.getHorarioMedico().getHoraSalida())) {

                        Cita nuevaCita = new Cita();
                        nuevaCita.setFechaCreacion(LocalDateTime.now());
                        nuevaCita.setFechaCita(fechaInicioCitaDTO);
                        nuevaCita.setMotivo(citaDTO.motivo());
                        nuevaCita.setNombrePaciente(citaDTO.nombrePaciente());
                        nuevaCita.setNombreMedico(citaDTO.nombreMedico());
                        nuevaCita.setEstadoCita(EstadoCita.PROGRAMADA);

                    }


                }

            }



        }else{
            String mensaje = "Fecha no valida";
        }

        List<HorarioMedico> horarios = horarioRepo.findAllByMedicoCodigo(id);

        List<HorarioDTO> horariosDTO = new ArrayList<>();

        for( HorarioMedico h : horarios ){
            horariosDTO.add( new HorarioDTO(
                    h.getDia(),
                    h.getHoraInicio(),
                    h.getHoraSalida()
            ) );
        }


    }
    @Override
    public DetalleMedicoCitaDTO obtenerMedico(int id) throws Exception {

        Optional<Medico> opcional = medicoRepo.findById(id);

        if( opcional.isEmpty() ){
            throw new Exception("No existe un médico con el código "+ id);
        }

        Medico buscado = opcional.get();

        List<HorarioMedico> horarios = horarioRepo.findAllByMedicoCodigo(id);

        List<HorarioDTO> horariosDTO = new ArrayList<>();

        for( HorarioMedico h : horarios ){
            horariosDTO.add( new HorarioDTO(
                    h.getDia(),
                    h.getHoraInicio(),
                    h.getHoraSalida()
            ) );
        }

        return new DetalleMedicoCitaDTO(
                buscado.getId(),
                buscado.getNombre(),
                buscado.getEspecialidad(),
                buscado.getUrlFoto(),
                horariosDTO
        );

    }

    @Override
    public int crearPQRS(RegistroPqrsDTO pqrsDTO) throws Exception{

        //Falta validar que no haya mas de 3 pqrs activas
        Pqrs nuevoPqrs = new Pqrs();
        nuevoPqrs.setNombrePaciente(pqrsDTO.NombrePaciente());
        nuevoPqrs.setFechaCreacion(LocalDateTime.now());
        nuevoPqrs.setMotivo(pqrsDTO.motivo());
        nuevoPqrs.setEstadoPqrs(EstadoPqrs.EN_PROCESO);
        nuevoPqrs.setMensajes((Mensaje) pqrsDTO.mensajes());

        return nuevoPqrs.getId();
    }

    private List<RespuestaDTO> convertirRespuestasDTO(List<Mensaje> mensajes) {

        return mensajes.stream().map(m -> new RespuestaDTO(
                m.getId(),
                m.getMensaje(),
                m.getCuenta().getCorreo(),
                m.getFechaMensaje()
        )).toList();
    }

    @Override
    public void listarPQRSPaciente() throws Exception{

    }

    @Override
    public void responderPQRS() throws Exception{

    }

    @Override
    public void listarCitasPaciente() throws Exception{

    }

    @Override
    public void filtrarCitasPorFecha(LocalDateTime fechaCitaDTO) throws Exception{

    }

    @Override
    public void filtrarCitasPorMedico() throws Exception{

    }

    @Override
    public DetalleCitaDTO verDetalleCita(DetalleCitaDTO citaDTO , int id) throws Exception{

        Optional<Cita> optional = citaRepo.findById(id);

        if(optional.isEmpty()){
            throw new Exception("No existe una cita con el código "+ id);
        }

        Cita buscado = optional.get();

        return new DetalleCitaDTO(
                buscado.getId(),
                buscado.getFechaCita(),
                buscado.getMotivo(),
                buscado.getEstadoCita(),
                buscado.getMedico().getNombre(),
                buscado.getMedico().getEspecialidad()
        );

    }
}
