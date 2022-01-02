package pl.mikolaj.clinic_appointment_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mikolaj.clinic_appointment_system.dto.PatientDto;
import pl.mikolaj.clinic_appointment_system.entity.Appointment;
import pl.mikolaj.clinic_appointment_system.entity.Patient;
import pl.mikolaj.clinic_appointment_system.entity.User;
import pl.mikolaj.clinic_appointment_system.entity.UserRole;
import pl.mikolaj.clinic_appointment_system.repository.PatientRepository;
import pl.mikolaj.clinic_appointment_system.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Patient> findAllPatients() {return patientRepository.findAll();}

    public Optional<Patient> findPatientById(Long id) {return patientRepository.findById(id);}

    public Optional<Patient> findPatientByUser(User user){return patientRepository.findPatientByUser(user);}

    public Patient addPatient(Patient patient) {return patientRepository.save(patient);}

    public void deletePatient(Patient patient) {
        patientRepository.delete(patient);
    }

//    public ResponseEntity<Patient> createPatient(PatientDto patientdto){
//        User user1 = userRepository.findUserByIdNumber(patientdto.getIdNumber());
//
//        if (user1 == null){
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            User user = new User(patientdto.getIdNumber(), patientdto.getEmail(), patientdto.getPassword(), patientdto.getFirstName(), patientdto.getLastName(), patientdto.getPhoneNumber(), patientdto.getAddress(), new ArrayList<>(), UserRole.PATIENT);
//
//            user.setPassword(encoder.encode(user.getPassword()));
//
//            Patient patient = new Patient(user, 30., 50.);
//
//            userRepository.save(user);
//            patientRepository.save(patient);
//            return ResponseEntity.ok(patient);
//        }
//        return null;
//    }

}
