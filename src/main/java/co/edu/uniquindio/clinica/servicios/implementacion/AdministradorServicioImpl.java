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

public class AdministradorServicioImpl implements AdministradorServicio{

    private final MedicoRepo medicoRepo;
    private final PQRSRepo pqrsRepo;
    private final CitaRepo citaRepo;
    private final MensajeRepo mensajeRepo;
    private final CuentaRepo cuentaRepo;

    @Override
    public int crearMedico(RegistroMedicoDTO medicoDTO) throws Exception {

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
        return medicoNuevo.getCodigo();
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

        return respuesta;
    }

    @Override
    public DetalleMedicoDTO obtenerMedico(int codigo) throws Exception {

        Optional<Medico> opcional =medicoRepo.findById(codigo);

        if( opcional.isEmpty() ){
            throw new Exception("No existe un médico con el código "+codigo);
        }

        Medico buscado = opcional.get();

        return new DetalleMedicoDTO(
                buscado.getCodigo(),
                buscado.getNombre(),
                buscado.getCedula(),
                buscado.getCiudad(),
                buscado.getEspecialidad(),
                buscado.getTelefono(),
                buscado.getCorreo(),
                buscado.getUrlFoto(),
                new ArrayList<>()
        );

    }

    @Override
    public List<ItemPQRSDTO> listarPQRS() throws Exception {

        List<Pqrs> listaPqrs = pqrsRepo.findAll();//select * from pqrs
        List<ItemPQRSDTO> respuesta = new ArrayList<>();

        for( Pqrs p : listaPqrs ){

            respuesta.add( new ItemPQRSDTO(
                    p.getCodigo(),
                    p.getEstado(),
                    p.getMotivo(),
                    p.getFechaCreacion(),
                    p.getCita().getPaciente().getNombre()
            ) );

        }

        /*List<ItemPQRSDTO> lista = listaPqrs.stream().map( p -> new ItemPQRSDTO(
                p.getCodigo(),
                p.getEstado(),
                p.getMotivo(),
                p.getFechaCreacion(),
                p.getCita().getPaciente().getNombre()
        ) ).toList();*/

        return respuesta;
    }

    @Override
    public DetallePQRSDTO verDetallePQRS(int codigo) throws Exception {

        Optional<Pqrs> opcional = pqrsRepo.findById(codigo);

        if( opcional.isEmpty() ){
            throw new Exception("El código "+codigo+" no está asociado a ningún PQRS");
        }

        Pqrs pqrs = opcional.get();

        return new DetallePQRSDTO(
                pqrs.getCodigo(),
                pqrs.getEstado(),
                pqrs.getMotivo(),
                pqrs.getCita().getPaciente().getNombre(),
                pqrs.getCita().getMedico().getNombre(),
                pqrs.getCita().getMedico().getEspecialidad(),
                pqrs.getFechaCreacion(),
                new ArrayList<>()
        );
    }

    @Override
    public int responderPQRS(RegistroRespuestaDTO registroRespuestaDTO) throws Exception {

        //Obtener el PQRS
        Optional<Pqrs> opcionalPqrs = pqrsRepo.findById(registroRespuestaDTO.codigoPQRS());

        if( opcionalPqrs.isEmpty() ){
            throw new Exception("El código "+registroRespuestaDTO.codigoPQRS()+" no está asociado a ningún PQRS");
        }

        //Obtener la cuenta
        Optional<Cuenta> opcionalCuenta = cuentaRepo.findById(registroRespuestaDTO.codigoCuenta());

        if( opcionalCuenta.isEmpty() ){
            throw new Exception("El código "+registroRespuestaDTO.codigoCuenta()+" no está asociado a ningún PQRS");
        }

        Mensaje mensaje = new Mensaje();
        mensaje.setFecha( LocalDateTime.now() );
        mensaje.setContenido(registroRespuestaDTO.mensaje() );
        mensaje.setPqrs( opcionalPqrs.get() );
        mensaje.setCuenta( opcionalCuenta.get() );

        return mensajeRepo.save(mensaje).getCodigo();
    }

    @Override
    public void cambiarEstadoPQRS(int codigoPQRS, EstadoPQRS estadoPQRS) throws Exception {

        Optional<Pqrs> opcional = pqrsRepo.findById(codigoPQRS);

        if( opcional.isEmpty() ){
            throw new Exception("El código "+codigoPQRS+" no está asociado a ningún PQRS");
        }

        Pqrs pqrs = opcional.get();
        pqrs.setEstado(estadoPQRS);

        pqrsRepo.save(pqrs);

    }

    @Override
    public List<ItemCitaAdminDTO> listarCitas() throws Exception {

        List<Cita> listaCitas = citaRepo.findAll();//select * from pqrs
        List<ItemCitaAdminDTO> respuesta = new ArrayList<>();

        for( Cita c : listaCitas ){

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
