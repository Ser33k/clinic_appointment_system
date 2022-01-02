package pl.mikolaj.clinic_appointment_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mikolaj.clinic_appointment_system.dto.AppointmentDto;
import pl.mikolaj.clinic_appointment_system.entity.*;
import pl.mikolaj.clinic_appointment_system.repository.*;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AvailabilityDateRepository availabilityDateRepository;

    @Autowired
    private AvailabilityDateService availabilityDateService;

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Appointment> findAllAppointments() {return appointmentRepository.findAll();}

    public Optional<Appointment> findAppointmentById(Long id) {return appointmentRepository.findById(id);}

    public void deleteAppointment(Appointment appointment) {
        appointmentRepository.delete(appointment);
    }

    public Appointment createAppointment(AppointmentDto appointmentDto){

        Optional<Patient> patientOptional = patientRepository.findPatientByUser(appointmentDto.getUser());
        Patient patient = null;
        if (patientOptional.isPresent()){
            patient = patientOptional.get();
        }
        Optional<Doctor> doctorOptional = doctorRepository.findDoctorByUser(appointmentDto.getAppointmentDate().getDoctor().getUser());
        Doctor doctor = null;
        if (doctorOptional.isPresent()){
            doctor = doctorOptional.get();
        }
        Appointment appointment = new Appointment(appointmentDto.getAppointmentDate(), AppointmentStatus.SCHEDULED, appointmentDto.getDescription(), patient);

        List<AvailabilityDate> availabilityDates = availabilityDateService.findAvailabilityDateByDoctor(doctor.getIdDoctor());
        Optional<AvailabilityDate> adOptional = availabilityDates.stream().filter(a -> a.getDate().isEqual(appointment.getAppointmentDate().getDate())).findFirst();
        if (adOptional.isPresent() && adOptional.get().isFree()){
            adOptional.get().setFree(false);
            availabilityDateRepository.save(adOptional.get());
            User userPatient = patient.getUser();
            User userDoctor = doctor.getUser();
            userPatient.getAppointmentList().add(appointment);
            if (userDoctor.getAppointmentList() == null){
                userDoctor.setAppointmentList(new ArrayList<>());
            }
            userDoctor.getAppointmentList().add(appointment);
            appointmentRepository.save(appointment);
            userRepository.save(userPatient);
            userRepository.save(userDoctor);
        }
        return appointment;
    }


//
    public List<Appointment> findAppointmentsByStatus(AppointmentStatus status){
        return appointmentRepository.findAppointmentsByStatus(status);
    }
}
