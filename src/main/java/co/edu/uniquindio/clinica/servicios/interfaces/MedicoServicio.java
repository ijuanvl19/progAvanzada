package co.edu.uniquindio.clinica.servicios.interfaces;

import org.springframework.stereotype.Service;

@Service
public interface MedicoServicio {

    void listarCitasPendientes() throws Exception;

    void listarCitasPendientesDia() throws Exception;

    void atenderCita() throws Exception;

    void listarCitasPaciente() throws Exception; //historial médico

    void agendarDiaLibre() throws Exception;

    void listarCitasRealizadasMedico() throws Exception;
}
