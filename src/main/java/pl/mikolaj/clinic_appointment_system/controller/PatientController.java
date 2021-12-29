package pl.mikolaj.clinic_appointment_system.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mikolaj.clinic_appointment_system.dto.PatientDto;
import pl.mikolaj.clinic_appointment_system.dto.UserLoginDto;
import pl.mikolaj.clinic_appointment_system.entity.Patient;
import pl.mikolaj.clinic_appointment_system.entity.User;
import pl.mikolaj.clinic_appointment_system.service.PatientService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000"}, maxAge = 3600)
@Data
@RestController
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/patients")
    public List<Patient> findAllPatients(){
        return patientService.findAllPatients();
    }

    @GetMapping("/patients/{id}")
    public Optional<Patient> findPatientById(@PathVariable(value = "id") Long id){
        return patientService.findPatientById(id);
    }

    @PostMapping("/patients")
    public ResponseEntity<Patient> addPatient(@RequestBody PatientDto patient){
        return patientService.createPatient(patient);
    }




}
