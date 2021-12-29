package pl.mikolaj.clinic_appointment_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pl.mikolaj.clinic_appointment_system.dto.DoctorDto;
import pl.mikolaj.clinic_appointment_system.dto.PatientDto;
import pl.mikolaj.clinic_appointment_system.entity.*;
import pl.mikolaj.clinic_appointment_system.service.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ClinicAppointmentSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicAppointmentSystemApplication.class, args);
    }

    private UserService userService;
    private DoctorService doctorService;
    private PatientService patientService;
    private AppointmentService appointmentService;
    private final AvailabilityDateService availabilityDateService;

    public ClinicAppointmentSystemApplication(UserService userService, DoctorService doctorService, PatientService patientService, AppointmentService appointmentService, AvailabilityDateService availabilityDateService) {
        this.userService = userService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.appointmentService = appointmentService;
        this.availabilityDateService = availabilityDateService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fill(){
////        Plant p = plantRepo.findById(2L).get();
////        Measurement measurement = new Measurement(25, 32, 75, LocalDateTime.now(), p);
////        measurementService.save(measurement);
//
//            User userPatient = new User("01010", "pacjent@o2.pl","pacjent", "Mikolaj", "Kiernikowski", "555", "addr", new ArrayList(), UserRole.PATIENT);
//            User userDoctor = new User("010102", "doktor@o2.pl","doktor", "Dr Piotr", "Napierala", "555", "addr", new ArrayList<>(), UserRole.DOCTOR);
//
//            userService.createUser(userDoctor);
//            userService.createUser(userPatient);

            DoctorDto doctorDto = new DoctorDto("0101022","doktor@o2.pl", "doktor", "Dr Piotr", "Napierala", "555-555-55", "adddr", "4214fsa3");

            doctorService.createDoctor(doctorDto);

            Doctor doctor = doctorService.findAllDoctors().get(0);
            AvailabilityDate ad = new AvailabilityDate(LocalDateTime.parse("2018-12-30T19:34:50.63"),true, 30,doctor);
            AvailabilityDate ad1 = new AvailabilityDate(LocalDateTime.parse("2019-12-30T19:34:50.63"),true, 30,doctor);
            AvailabilityDate ad2 = new AvailabilityDate(LocalDateTime.parse("2020-12-30T19:34:50.63"),true, 30,doctor);
//
            availabilityDateService.createAvailabilityDate(ad);
            availabilityDateService.createAvailabilityDate(ad1);
            availabilityDateService.createAvailabilityDate(ad2);
//            doctor.setAvailabilityDates(List.of(ad,ad1,ad2));
//
//            doctorService.saveDoctor(doctor);
//
            PatientDto patientDto = new PatientDto("98202012","pacjent@o2.pl", "pacjent", "Mikolaj", "Kiernik","555-55", "adrrr");

            patientService.createPatient(patientDto);
//
//            Appointment appointment = new Appointment(ad, AppointmentStatus.SCHEDULED, patient);
//
//            appointmentService.createAppointment(appointment);
//
    }
}
