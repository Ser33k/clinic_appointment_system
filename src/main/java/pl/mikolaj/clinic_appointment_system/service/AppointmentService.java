package pl.mikolaj.clinic_appointment_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mikolaj.clinic_appointment_system.dto.AppointmentDto;
import pl.mikolaj.clinic_appointment_system.dto.ReserveAppointmentDto;
import pl.mikolaj.clinic_appointment_system.dto.ResultAppointmentDto;
import pl.mikolaj.clinic_appointment_system.entity.*;
import pl.mikolaj.clinic_appointment_system.repository.*;

import javax.print.Doc;
import java.time.LocalDateTime;
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

    @Autowired
    private UserService userService;

    public List<Appointment> findAllAppointments() {return appointmentRepository.findAll();}

    public Optional<Appointment> findAppointmentById(Long id) {return appointmentRepository.findById(id);}

    public void deleteAppointment(Appointment appointment) {
        appointmentRepository.delete(appointment);
    }

//    public Appointment createAppointment(AppointmentDto appointmentDto){
//
//        Optional<Patient> patientOptional = patientRepository.findPatientByUser(appointmentDto.getUser());
//        Patient patient = null;
//        if (patientOptional.isPresent()){
//            patient = patientOptional.get();
//        }
//        Optional<Doctor> doctorOptional = doctorRepository.findDoctorByUser(appointmentDto.getAppointmentDate().getDoctor().getUser());
//        Doctor doctor = null;
//        if (doctorOptional.isPresent()){
//            doctor = doctorOptional.get();
//        }
//        Appointment appointment = new Appointment(appointmentDto.getAppointmentDate(), AppointmentStatus.SCHEDULED, appointmentDto.getDescription(), patient);
//
//        List<AvailabilityDate> availabilityDates = availabilityDateService.findAvailabilityDateByDoctor(doctor.getIdDoctor());
//        Optional<AvailabilityDate> adOptional = availabilityDates.stream().filter(a -> a.getDate().isEqual(appointment.getAppointmentDate().getDate())).findFirst();
//        if (adOptional.isPresent() && adOptional.get().isFree()){
//            adOptional.get().setFree(false);
//            availabilityDateRepository.save(adOptional.get());
//            User userPatient = patient.getUser();
//            User userDoctor = doctor.getUser();
//            userPatient.getAppointmentList().add(appointment);
//            if (userDoctor.getAppointmentList() == null){
//                userDoctor.setAppointmentList(new ArrayList<>());
//            }
//            userDoctor.getAppointmentList().add(appointment);
//            appointmentRepository.save(appointment);
//            userRepository.save(userPatient);
//            userRepository.save(userDoctor);
//        }
//        return appointment;
//    }


//
    public List<Appointment> findAppointmentsByStatus(AppointmentStatus status){
        return appointmentRepository.findAppointmentsByStatus(status);
    }

    public ResultAppointmentDto reserveAppointment(ReserveAppointmentDto reserveAppointmentDto) {

        int result=0;
        ResultAppointmentDto resultAppointmentDto = new ResultAppointmentDto();
        Object [] users = userService.findUser(reserveAppointmentDto.getPatientDto(), reserveAppointmentDto.getDoctorDto());

        Patient patient = (Patient) users[0];
        Doctor doctor = (Doctor) users[1];
        if(patient == null)
        {
            resultAppointmentDto.setPatientIdNumber(null);
            result=1;
        }
        if (doctor == null)
        {
            resultAppointmentDto.setDoctorLicenseNumber(null);
            result++;
        }
        if(result>0)
            return resultAppointmentDto;

        Appointment appointment = findFreeTerm(users, reserveAppointmentDto.getDate(), reserveAppointmentDto.getDuration());
        appointment.setDescription(reserveAppointmentDto.getDescription());
        resultAppointmentDto.setDescription(reserveAppointmentDto.getDescription());

        if (appointment.getAppointmentDate() == null){
            resultAppointmentDto.setDate(null);
            return resultAppointmentDto;
        }

        patient.getUser().getAppointmentList().add(appointment);
        doctor.getUser().getAppointmentList().add(appointment);

        appointmentRepository.save(appointment);

        userRepository.save(patient.getUser());
        userRepository.save(doctor.getUser());

        return resultAppointmentDto;
    }

    public Appointment findFreeTerm (Object []users, LocalDateTime date, int durationMinutes)
    {
        Patient patient = (Patient) users[0];
        Doctor doctor = (Doctor)users[1];

        List<AvailabilityDate> availabilityDates = doctor.getAvailabilityDates();

        Optional<AvailabilityDate> adOptional = availabilityDates.stream().filter(a -> a.getDate().isEqual(date)).findFirst();

        if (adOptional.isPresent()){
            AvailabilityDate ad = adOptional.get();
            if (ad.isFree() && ad.getDurationMinutes() > durationMinutes){
                ad.setFree(false);
                availabilityDateRepository.save(ad);

                return new Appointment(ad, AppointmentStatus.SCHEDULED, null, patient);
            }
        }
        return new Appointment(null, AppointmentStatus.SCHEDULED, null, patient);
    }
}
