package co.edu.uniquindio.clinica.servicios.implementacion;


import co.edu.uniquindio.clinica.repositorios.*;
import co.edu.uniquindio.clinica.servicios.interfaces.MedicoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicoServicioImpl implements MedicoServicio {

    private final MedicoRepo medicoRepo;
    private final PqrsRepo pqrsRepo;
    private final CuentaRepo cuentaRepo;
    private final MensajeRepo mensajeRepo;
    private final CitaRepo citaRepo;
    private final HorarioRepo horarioRepo;

    @Override
    public void listarCitasPendientes() {



    }

    @Override
    public void listarCitasPendientesDia() {

    }

    @Override
    public void atenderCita() {

    }

    @Override
    public void listarCitasPaciente() {

    }

    @Override
    public void agendarDiaLibre() {

    }

    @Override
    public void listarCitasRealizadasMedico() {

    }
}
