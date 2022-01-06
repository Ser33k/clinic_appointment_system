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

    public List<Patient> findAllPatients() {return patientRepository.findAll();}

    public Optional<Patient> findPatientById(Long id) {return patientRepository.findById(id);}

}
