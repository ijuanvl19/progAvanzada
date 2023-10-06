package co.edu.uniquindio.clinica.servicios.implementacion;

import co.edu.uniquindio.clinica.dto.DetallePQRSDTO;
import co.edu.uniquindio.clinica.dto.ItemPQRSDTO;
import co.edu.uniquindio.clinica.dto.RegistroRespuestaDTO;
import co.edu.uniquindio.clinica.dto.admin.DetalleMedicoDTO;
import co.edu.uniquindio.clinica.dto.admin.ItemCitaAdminDTO;
import co.edu.uniquindio.clinica.dto.admin.ItemMedicoDTO;
import co.edu.uniquindio.clinica.dto.admin.RegistroMedicoDTO;
import co.edu.uniquindio.clinica.modelo.entidades.*;
import co.edu.uniquindio.clinica.modelo.enums.EstadoPQRS;
import co.edu.uniquindio.clinica.modelo.enums.EstadoUsuario;
import co.edu.uniquindio.clinica.repositorios.*;
import co.edu.uniquindio.clinica.servicios.interfaces.AdministradorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class AdministradorServicioImpl implements AdministradorServicio {

    private final MedicoRepo medicoRepo;
    private final PQRSRepo pqrsRepo;
    private final CuentaRepo cuentaRepo;
    private final MensajeRepo mensajeRepo;
    private final CitaRepo citaRepo;
    private final HorarioRepo horarioRepo;

    @Override
    public int crearMedico(RegistroMedicoDTO medicoDTO) throws Exception {

        if( estaRepetidaCedula(medicoDTO.cedula()) ){
            throw new Exception("La cédula "+medicoDTO.cedula()+" ya está en uso");
        }

        if( estaRepetidoCorreo(medicoDTO.correo()) ){
            throw new Exception("El correo "+medicoDTO.cedula()+" ya está en uso");
        }

        Medico medico = new Medico();
        medico.setCedula(medicoDTO.cedula() );
        medico.setTelefono(medicoDTO.telefono());
        medico.setNombre(medicoDTO.nombre() );
        medico.setEspecialidad( medicoDTO.especialidad() );
        medico.setCiudad(medicoDTO.ciudad());
        medico.setCorreo(medicoDTO.correo() );
        medico.setPassword(medicoDTO.password());
        medico.setUrlFoto(medicoDTO.urlFoto());
        medico.setEstado(EstadoUsuario.ACTIVO);

        Medico medicoNuevo = medicoRepo.save(medico);

        asignarHorariosMedico( medicoNuevo, medicoDTO.horarios() );

        return medicoNuevo.getCodigo();
    }

    private void asignarHorariosMedico(Medico medicoNuevo, List<HorarioDTO> horarios) {

        for( HorarioDTO h : horarios ){

            HorarioMedico hm = new HorarioMedico();
            hm.setDia( h.dia() );
            hm.setHoraInicio( h.horaInicio() );
            hm.setHoraFin( h.horaSalida() );
            hm.setMedico( medicoNuevo );

            horarioRepo.save(hm);

        }

    }


    private boolean estaRepetidoCorreo(String correo) {
        return medicoRepo.findByCorreo(correo) != null;
    }

    private boolean estaRepetidaCedula(String cedula) {
        return medicoRepo.findByCedula(cedula) != null;
    }

    @Override
    public int actualizarMedico(DetalleMedicoDTO medicoDTO) throws Exception {

        Optional<Medico> opcional =medicoRepo.findById(medicoDTO.codigo());

        if( opcional.isEmpty() ){
            throw new Exception("No existe un médico con el código "+medicoDTO.codigo());
        }

        Medico buscado = opcional.get();

        buscado.setCedula(medicoDTO.cedula() );
        buscado.setTelefono(medicoDTO.telefono());
        buscado.setNombre(medicoDTO.nombre() );
        buscado.setEspecialidad( medicoDTO.especialidad() );
        buscado.setCiudad(medicoDTO.ciudad());
        buscado.setCorreo(medicoDTO.correo() );
        buscado.setUrlFoto(medicoDTO.urlFoto());

        medicoRepo.save( buscado );

        return buscado.getCodigo();
    }

    @Override
    public void eliminarMedico(int codigo) throws Exception {

        Optional<Medico> opcional =medicoRepo.findById(codigo);

        if( opcional.isEmpty() ){
            throw new Exception("No existe un médico con el código "+codigo);
        }

        Medico buscado = opcional.get();
        buscado.setEstado(EstadoUsuario.INACTIVO);
        medicoRepo.save( buscado );

        //medicoRepo.delete(buscado);

    }

    @Override
    public List<ItemMedicoDTO> listarMedicos() throws Exception {

        List<Medico> medicos = medicoRepo.findAll();

        if(medicos.isEmpty()){
            throw new Exception("No hay médicos registrados");
        }

        List<ItemMedicoDTO> respuesta = new ArrayList<>();

        for(Medico m: medicos){
            respuesta.add( new ItemMedicoDTO(
                    m.getCodigo(),
                    m.getCedula(),
                    m.getNombre(),
                    m.getUrlFoto(),
                    m.getEspecialidad()) );
        }

        /*List<ItemMedicoDTO> respuesta = medicos.stream().map( m -> new ItemMedicoDTO(
                m.getCodigo(),
                m.getCedula(),
                m.getNombre(),
                m.getUrlFoto(),
                m.getEspecialidad()
        ) ).toList();*/

        return respuesta;
    }

    @Override
    public DetalleMedicoDTO obtenerMedico(int codigo) throws Exception {

        Optional<Medico> opcional =medicoRepo.findById(codigo);

        if( opcional.isEmpty() ){
            throw new Exception("No existe un médico con el código "+codigo);
        }

        Medico buscado = opcional.get();

        List<HorarioMedico> horarios = horarioRepo.findAllByMedicoCodigo(codigo);
        List<HorarioDTO> horariosDTO = new ArrayList<>();

        for( HorarioMedico h : horarios ){
            horariosDTO.add( new HorarioDTO(
                    h.getDia(),
                    h.getHoraInicio(),
                    h.getHoraFin()
            ) );
        }

        return new DetalleMedicoDTO(
                buscado.getCodigo(),
                buscado.getNombre(),
                buscado.getCedula(),
                buscado.getCiudad(),
                buscado.getEspecialidad(),
                buscado.getTelefono(),
                buscado.getCorreo(),
                buscado.getUrlFoto(),
                horariosDTO
        );

    }

    @Override
    public List<ItemPQRSDTO> listarPQRS() throws Exception {

        List<Pqrs> listaPqrs = pqrsRepo.findAll();
        List<ItemPQRSDTO> respuesta = new ArrayList<>();

        for( Pqrs p: listaPqrs ){

            respuesta.add( new ItemPQRSDTO(
                    p.getCodigo(),
                    p.getEstado(),
                    p.getMotivo(),
                    p.getFechaCreacion(),
                    p.getCita().getPaciente().getNombre()
            ) );

        }

        return respuesta;
    }

    @Override
    public DetallePQRSDTO verDetallePQRS(int codigo) throws Exception {

        Optional<Pqrs> opcional = pqrsRepo.findById(codigo);

        if(opcional.isEmpty()){
            throw new Exception("No existe un PQRS con el código "+codigo);
        }

        Pqrs buscado = opcional.get();
        List<Mensaje> mensajes = mensajeRepo.findAllByPqrsCodigo(codigo);

        return new DetallePQRSDTO(
                buscado.getCodigo(),
                buscado.getEstado(),
                buscado.getMotivo(),
                buscado.getCita().getPaciente().getNombre(),
                buscado.getCita().getMedico().getNombre(),
                buscado.getCita().getMedico().getEspecialidad(),
                buscado.getFechaCreacion(),
                convertirRespuestasDTO(mensajes)
        );
    }

    private List<RespuestaDTO> convertirRespuestasDTO(List<Mensaje> mensajes) {
        return mensajes.stream().map(m -> new RespuestaDTO(
                m.getCodigo(),
                m.getContenido(),
                m.getCuenta().getCorreo(),
                m.getFecha()
        )).toList();
    }

    @Override
    public int responderPQRS(RegistroRespuestaDTO registroRespuestaDTO) throws Exception {

        Optional<Pqrs> opcionalPQRS = pqrsRepo.findById(registroRespuestaDTO.codigoPQRS());

        if(opcionalPQRS.isEmpty()){
            throw new Exception("No existe un PQRS con el código "+registroRespuestaDTO.codigoPQRS());
        }

        Optional<Cuenta> opcionalCuenta = cuentaRepo.findById(registroRespuestaDTO.codigoCuenta());

        if(opcionalCuenta.isEmpty()){
            throw new Exception("No existe una cuenta con el código "+registroRespuestaDTO.codigoCuenta());
        }

        Mensaje mensajeNuevo = new Mensaje();
        mensajeNuevo.setPqrs(opcionalPQRS.get());
        mensajeNuevo.setFecha( LocalDateTime.now() );
        mensajeNuevo.setCuenta(opcionalCuenta.get());
        mensajeNuevo.setContenido(registroRespuestaDTO.mensaje() );

        Mensaje respuesta = mensajeRepo.save(mensajeNuevo);

        return respuesta.getCodigo();
    }

    @Override
    public void cambiarEstadoPQRS(int codigoPQRS, EstadoPQRS estadoPQRS) throws Exception {

        Optional<Pqrs> opcional = pqrsRepo.findById(codigoPQRS);

        if( opcional.isEmpty() ){
            throw new Exception("No existe un PQRS con el código "+codigoPQRS);
        }

        Pqrs pqrs = opcional.get();
        pqrs.setEstado( estadoPQRS );

        pqrsRepo.save( pqrs );
    }

    @Override
    public List<ItemCitaAdminDTO> listarCitas() throws Exception {

        List<Cita> citas = citaRepo.findAll();
        List<ItemCitaAdminDTO> respuesta = new ArrayList<>();

        if(citas.isEmpty()){
            throw new Exception("No existen citas creadas");
        }

        for( Cita c : citas ){
            respuesta.add( new ItemCitaAdminDTO(
                    c.getCodigo(),
                    c.getPaciente().getCedula(),
                    c.getPaciente().getNombre(),
                    c.getMedico().getNombre(),
                    c.getMedico().getEspecialidad(),
                    c.getEstado(),
                    c.getFechaCita()
            ) );
        }

        return respuesta;
    }
}