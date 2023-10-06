package co.edu.uniquindio.clinica.servicios;

import org.springframework.stereotype.Service;

@Service
public interface MedicoServicio {

    void listarCitasPendientes();

    void atenderCita();

    void listarCitasPaciente(); //historial médico

    void agendarDiaLibre();

    void listarCitasRealizadasMedico();
}
