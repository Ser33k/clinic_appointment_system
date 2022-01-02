package pl.mikolaj.clinic_appointment_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mikolaj.clinic_appointment_system.dto.DoctorDto;
import pl.mikolaj.clinic_appointment_system.dto.PatientDto;
import pl.mikolaj.clinic_appointment_system.dto.ScheduleAppointmentDto;
import pl.mikolaj.clinic_appointment_system.entity.*;
import pl.mikolaj.clinic_appointment_system.repository.DoctorRepository;
import pl.mikolaj.clinic_appointment_system.repository.PatientRepository;
import pl.mikolaj.clinic_appointment_system.repository.UserRepository;

import javax.print.Doc;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Doctor> findAllDoctors() {return doctorRepository.findAll();}

    public Optional<Doctor> findDoctorById(Long id) {return doctorRepository.findById(id);}

    public Doctor addDoctor(Doctor doctor) {return doctorRepository.save(doctor);}

    public void deleteDoctor(Doctor doctor) {
        doctorRepository.delete(doctor);
    }

    public Doctor createDoctor(Doctor doctor){
        doctorRepository.save(doctor);
        return doctor;
    }

    public List<ScheduleAppointmentDto> findDoctorsAppointments(String licenseNumber) {
        Doctor doctor = doctorRepository.findDoctorByLicenseNumber(licenseNumber);

        if (doctor == null){
            return null;
        }

        List<Appointment> doctorsAppointments = doctor.getUser().getAppointmentList();

        List<Appointment> scheduledAppointments = doctorsAppointments.stream().filter(appointment -> appointment.getStatus() == AppointmentStatus.SCHEDULED).collect(Collectors.toList());
        return createAppointmentDtoList(scheduledAppointments);
    }

    private List<ScheduleAppointmentDto> createAppointmentDtoList(List<Appointment> doctorsAppointments) {
        List<ScheduleAppointmentDto> dtoList = new ArrayList<>();
        for (Appointment a: doctorsAppointments
             ) {
            dtoList.add(new ScheduleAppointmentDto(a.getAppointmentDate().getDate(), a.getPatient().getUser().getFirstName(), a.getPatient().getUser().getLastName(), a.getDescription()));
        }

        return dtoList;
    }

//    public ResponseEntity<Doctor> createDoctor(DoctorDto doctorDto){
//        User user1 = userRepository.findUserByIdNumber(doctorDto.getIdNumber());
//
//        if (user1 == null){
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            User user = new User(doctorDto.getIdNumber(), doctorDto.getEmail(), doctorDto.getPassword(), doctorDto.getFirstName(), doctorDto.getLastName(), doctorDto.getPhoneNumber(), doctorDto.getAddress(), new ArrayList<>(), UserRole.DOCTOR);
//
//            user.setPassword(encoder.encode(user.getPassword()));
//
//            Doctor doctor = new Doctor(doctorDto.getLicenseNumber(), user, new ArrayList<AvailabilityDate>());
//
//            userRepository.save(user);
//            doctorRepository.save(doctor);
//            return ResponseEntity.ok(doctor);
//        }
//        return null;
//    }

    public void saveDoctor(Doctor doctor){
        doctorRepository.save(doctor);
    }


    public String getLicenseNumberByUserIdNumber(String userIdNumber) {
        Doctor doctor = doctorRepository.findDoctorByUserIdNumber(userIdNumber);

        return doctor.getLicenseNumber();
    }
}
