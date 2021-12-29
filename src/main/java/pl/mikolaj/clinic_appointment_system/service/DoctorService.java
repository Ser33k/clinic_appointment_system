package pl.mikolaj.clinic_appointment_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mikolaj.clinic_appointment_system.dto.DoctorDto;
import pl.mikolaj.clinic_appointment_system.dto.PatientDto;
import pl.mikolaj.clinic_appointment_system.entity.*;
import pl.mikolaj.clinic_appointment_system.repository.DoctorRepository;
import pl.mikolaj.clinic_appointment_system.repository.PatientRepository;
import pl.mikolaj.clinic_appointment_system.repository.UserRepository;

import javax.print.Doc;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<Doctor> createDoctor(DoctorDto doctorDto){
        User user1 = userRepository.findUserByIdNumber(doctorDto.getIdNumber());

        if (user1 == null){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            User user = new User(doctorDto.getIdNumber(), doctorDto.getEmail(), doctorDto.getPassword(), doctorDto.getFirstName(), doctorDto.getLastName(), doctorDto.getPhoneNumber(), doctorDto.getAddress(), new ArrayList<>(), UserRole.DOCTOR);

            user.setPassword(encoder.encode(user.getPassword()));

            Doctor doctor = new Doctor(doctorDto.getLicenseNumber(), user, new ArrayList<AvailabilityDate>());

            userRepository.save(user);
            doctorRepository.save(doctor);
            return ResponseEntity.ok(doctor);
        }
        return null;
    }

    public void saveDoctor(Doctor doctor){
        doctorRepository.save(doctor);
    }
}
