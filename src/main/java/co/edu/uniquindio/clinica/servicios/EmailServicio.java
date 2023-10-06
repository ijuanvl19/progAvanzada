package co.edu.uniquindio.clinica.servicios;

import co.edu.uniquindio.clinica.dto.EmailDTO;
import org.springframework.stereotype.Service;

@Service
public interface EmailServicio {

    String enviarCorreo(EmailDTO emailDTO) throws Exception;
}
