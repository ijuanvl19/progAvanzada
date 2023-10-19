package co.edu.uniquindio.clinica.servicios.implementacion;

import co.edu.uniquindio.clinica.dto.NewPasswordDTO;
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
    public int registrarse(RegistroPacienteDTO pacienteDTO) throws Exception {

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

    public boolean estaRepetidaCedula(String cedula) {return pacienteRepo.findByCedula(cedula) != null;
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

        buscado.setNombre(pacienteDTO.nombre());
        buscado.setCedula(pacienteDTO.cedula());
        buscado.setCorreo(pacienteDTO.correo());
        buscado.setCiudad(pacienteDTO.ciudad());
        buscado.setTipoSangre(pacienteDTO.tipoSangre());
        buscado.setTelefono(pacienteDTO.telefono());
        buscado.setUrlFoto(pacienteDTO.urlFoto());

        pacienteRepo.save(buscado);

        return buscado.getId();
    }

    @Override
    public DetallePacienteDTO verDetallePaciente(DetallePacienteDTO pacienteDTO) throws Exception {

        Optional<Paciente> optional = pacienteRepo.findById(pacienteDTO.id());

        if (optional.isEmpty()) {
            throw new Exception("El usuario no es un paciente registrado");
        }
            Paciente buscado = (Paciente) optional.get();

        return new DetallePacienteDTO(
                buscado.getId(),
                buscado.getNombre(),
                buscado.getCedula(),
                buscado.getCiudad(),
                buscado.getTelefono(),
                buscado.getCorreo(),
                buscado.getFechaNacimiento(),
                buscado.getAlergias(),
                buscado.getTipoSangre(),
                buscado.getEps(),
                buscado.getUrlFoto()
        );
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
    public void cambiarPassword(String passwordActualDTO, String passwordNewDTO,
                                 NewPasswordDTO pacienteDTO) throws Exception {

        String message = "";
        Optional<Paciente> optional = pacienteRepo.findById(pacienteDTO.id());

        if( optional.isEmpty() ){
            throw new Exception("No existe un paciente con el id " + pacienteDTO.id());
        }

        Paciente buscado = optional.get();

        if (buscado.getPassword().equals(passwordActualDTO) && pacienteDTO.cedula().equals(buscado.getCedula())) {

            buscado.setPassword(passwordNewDTO);

            pacienteRepo.save(buscado);
            message = "La contraseña se cambió satisfactoriamente";
        }else{
            throw new Exception("La contraseña actual no coincide");
        }
    }

    // Pendiente arreglarlo y enviar correos medico y paciente
    @Override
    public void crearCita(DetalleMedicoCitaDTO medicoDTO, RegistroCitaDTO citaDTO,
                            DetallePacienteDTO pacienteDTO, LocalDateTime fechaCita,
                            Especialidad especialidad) throws Exception {

        //DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy MMMM dd, HH:mm");
        LocalDateTime fechaSistema = LocalDateTime.now();
        //String fechaAhora = formato.format(fechaSistema);
        //String fechaInicioCitaFormateada = formato.format(fechaCita);

        List<Cita> citas =  citaRepo.findByPacienteCedula(pacienteDTO.cedula());
        List<Medico> medicos = medicoRepo.findAllByEspecialidad(especialidad);
        List<Cita> citasActivas = new ArrayList<>();

        if (medicos.isEmpty() ) {
            throw new Exception("No hay médicos registrados con esa especialidad");
        }

        for (Cita c: citas) {
            if (c.getEstadoCita().equals(EstadoCita.PROGRAMADA)) {
                citasActivas.add(c);
            }
        }

        if (fechaCita.isBefore(fechaSistema)) {
            
            throw new Exception("Fecha no valida");
            
        } else if (citasActivas.size() < 4) {

            List<HorarioMedico> horarios = horarioRepo.findByMedico_Id(medicoDTO.id());

            for (Medico m : medicos ) {

                for (HorarioMedico hm : horarios) {

                    if (hm.getDia().getDayOfWeek().equals(fechaCita.getDayOfWeek()) &&
                    hm.getHoraInicio().equals(fechaCita.getHour())) {

                        Cita nuevaCita = new Cita();
                        nuevaCita.setFechaCreacion(LocalDateTime.now());
                        nuevaCita.setFechaCita(fechaCita);
                        nuevaCita.setMotivo(citaDTO.motivo());
                        nuevaCita.getPaciente().setNombre(citaDTO.nombrePaciente());
                        nuevaCita.getMedico().setNombre(citaDTO.nombreMedico());
                        nuevaCita.setEstadoCita(EstadoCita.PROGRAMADA);
                        citaRepo.save(nuevaCita);
                    }
                    horarioRepo.save(hm);
                }
            }

        }else {
            throw new Exception("Ya tiene 3 citas agendadas. No puede agendar por el momento");
        }

    }

    @Override
    public int modificarCita(DetalleCitaDTO citaDTO) throws Exception {

        Optional<Cita> optional = citaRepo.findById(citaDTO.codigo());

        if (optional.isEmpty()) {
            throw new Exception("El codigo de cita no existe");
        }

        Cita buscado = optional.get();

        buscado.setFechaCita(citaDTO.fechaCita());
        buscado.setMotivo(citaDTO.motivo());
        buscado.getMedico().setNombre(citaDTO.nombreMedico());
        buscado.getMedico().setEspecialidad(citaDTO.especialidad());
        citaRepo.save(buscado);

        return buscado.getCodigo();

    }

    @Override
    public void cancelarCita(DetalleCitaDTO citaDTO) throws Exception {

        Optional<Cita> optional = citaRepo.findById(citaDTO.codigo());
        Cita buscado = optional.get();

        if (optional.isEmpty()) {
            throw new Exception("El codigo de cita no existe");
        }else {
            buscado.setEstadoCita(EstadoCita.CANCELADA);
            citaRepo.save(buscado);
        }
    }

    //Falta parte de enviar correo al administrador
    @Override
    public void crearPqrs(RegistroPqrsDTO pqrsDTO, DetallePacienteDTO pacienteDTO) throws Exception{

        List<Mensaje> mensajes = new ArrayList<>();
        List<Pqrs> pqrs = pqrsRepo.findAllById(pacienteDTO.id());

        List<Pqrs> prqsEnProceso = new ArrayList<>();

        for (Pqrs pq : pqrs) {
            if (pq.getEstadoPqrs().equals(EstadoPqrs.NUEVO)) {
                prqsEnProceso.add(pq);
            }
        }
        if (prqsEnProceso.size() < 4) {
            for (Pqrs p : pqrs) {
                Pqrs nuevaPqrs = new Pqrs();
                nuevaPqrs.setFechaCreacion(LocalDateTime.now());
                nuevaPqrs.setMotivo(pqrsDTO.motivo());
                nuevaPqrs.getCita().setCodigo(pqrsDTO.id());
                nuevaPqrs.getPaciente().setNombre(pqrsDTO.NombrePaciente());
                nuevaPqrs.setEstadoPqrs(EstadoPqrs.NUEVO);
                convertirRespuestasPacienteDTO(mensajes);
                pqrsRepo.save(nuevaPqrs);
            }
        }else{
            throw new Exception("Ya tiene 3 pqrs en proceso. No puede crear mas por el momento");
        }
    }

    private List<RespuestaPacienteDTO> convertirRespuestasPacienteDTO(List<Mensaje> mensajes) {

        return mensajes.stream().map(m -> new RespuestaPacienteDTO(
                m.getCodigo(),
                m.getContenido(),
                m.getCuenta().getCorreo(),
                m.getFechaMensaje()
        )).toList();
    }

    @Override
    public List<ItemPqrsPacienteDTO> listarPqrsPaciente(int id) throws Exception{

        List<Pqrs> pqrs =  pqrsRepo.findAllById(id);
        List<ItemPqrsPacienteDTO> listaPqrs = new ArrayList<>();

        if (pqrs.isEmpty()){
            throw new Exception("No tiene pqrs creadas");
        }

        for (Pqrs p: pqrs) {
            listaPqrs.add(new ItemPqrsPacienteDTO(
                    p.getCodigo(),
                    p.getEstadoPqrs(),
                    p.getMotivo(),
                    p.getFechaCreacion()
            ));
        }
        return listaPqrs;
    }

    //Hay que arreglar
    @Override
    public int responderPqrsPaciente(RegistroRespuestaPacienteDTO registroRespuestaPacienteDTO) throws Exception{

        Optional<Pqrs> opcionalPqrs = pqrsRepo.findById(registroRespuestaPacienteDTO.codigoPqrs());

        if(opcionalPqrs.isEmpty()){
            throw new Exception("No existe un PQRS con el código " + registroRespuestaPacienteDTO.codigoPqrs());
        }

        Optional<Cuenta> opcionalCuenta = cuentaRepo.findById(registroRespuestaPacienteDTO.codigoCuenta());

        if(opcionalCuenta.isEmpty()){
            throw new Exception("No existe una cuenta con el código "+ registroRespuestaPacienteDTO.codigoCuenta());
        }

        Mensaje mensajeNuevo = new Mensaje();
        mensajeNuevo.setPqrs(opcionalPqrs.get());
        mensajeNuevo.setFechaMensaje( LocalDateTime.now() );
        mensajeNuevo.setCuenta(opcionalCuenta.get());
        mensajeNuevo.setContenido(registroRespuestaPacienteDTO.mensaje() );

        Mensaje respuesta = mensajeRepo.save(mensajeNuevo);

        return respuesta.getCodigo();
    }

    @Override
    public List<DetalleCitaDTO> listarCitasPaciente(int id) throws Exception{

        List<Cita> listaCitas = citaRepo.findAllById(id);

        if (listaCitas.isEmpty()) {

            throw new Exception("No existen citas agendadas");
        }

        List<DetalleCitaDTO> citas =new ArrayList<>();

        for (Cita c : listaCitas) {

            citas.add(new DetalleCitaDTO(
                    c.getCodigo(),
                    c.getFechaCita(),
                    c.getMotivo(),
                    c.getEstadoCita(),
                    c.getMedico().getNombre(),
                    c.getMedico().getEspecialidad()
            ));
        }
        return citas;
    }

    @Override
    public List<ItemCitaDTO> filtrarCitasPorFecha(LocalDateTime fechaCita) throws Exception{

        List<Cita> citas = citaRepo.findAllByFechaCita(fechaCita);

        List<ItemCitaDTO> listaCitas = new ArrayList<>();

        for (Cita c : citas) {

            listaCitas.add(new ItemCitaDTO(
                    c.getCodigo(),
                    c.getFechaCita(),
                    c.getEstadoCita(),
                    c.getMedico().getNombre(),
                    c.getMedico().getEspecialidad()
            ));
        }
        return listaCitas;

    }

    @Override
    public List<ItemCitaDTO> filtrarCitasPorMedico(String nombreMedico) throws Exception{

        List<Cita> citas = citaRepo.findAllByMedicoNombre(nombreMedico);

        if (citas.isEmpty()) {
            throw new Exception("No existen citas con el medico asociado");
        }else {

            List<ItemCitaDTO> listaCitas = new ArrayList<>();

            for (Cita c : citas) {

                listaCitas.add(new ItemCitaDTO(
                        c.getCodigo(),
                        c.getFechaCita(),
                        c.getEstadoCita(),
                        c.getMedico().getNombre(),
                        c.getMedico().getEspecialidad()
                ));
            }
            return listaCitas;
        }
    }

    @Override
    public DetalleCitaDTO verDetalleCita(int id) throws Exception{

        Optional<Cita> optional = citaRepo.findById(id);

        if(optional.isEmpty()){
            throw new Exception("No existe una cita con el código "+ id);
        }

        Cita buscado = optional.get();

        return new DetalleCitaDTO(
                buscado.getCodigo(),
                buscado.getFechaCita(),
                buscado.getMotivo(),
                buscado.getEstadoCita(),
                buscado.getMedico().getNombre(),
                buscado.getMedico().getEspecialidad()
        );

    }
}
