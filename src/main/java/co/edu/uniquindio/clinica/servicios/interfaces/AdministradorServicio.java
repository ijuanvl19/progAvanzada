package co.edu.uniquindio.clinica.servicios.interfaces;

import co.edu.uniquindio.clinica.dto.administrador.*;
import co.edu.uniquindio.clinica.modelo.enums.EstadoPqrs;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdministradorServicio {

    int crearMedico(RegistroMedicoDTO medicoDTO) throws Exception;

    int actualizarMedico(DetalleMedicoDTO medicoDTO) throws Exception;

    void eliminarMedico(int codigo) throws Exception;

    List<ItemMedicoDTO> listarMedicos() throws Exception;

    DetalleMedicoDTO obtenerMedico(int codigo) throws Exception;

    List<ItemPqrsDTO> listarPqrs() throws Exception;

    DetallePqrsDTO verDetallePqrs(int codigo) throws Exception;

    List<ItemPqrsDTO> listarPQRS() throws Exception;

    int responderPQRS(RegistroRespuestaDTO registroRespuestaDTO) throws Exception;

    void cambiarEstadoPqrs(int codigoPqrs, EstadoPqrs estadoPqrs) throws Exception;

    List<ItemCitaAdminDTO> listarCitas() throws Exception;
}
