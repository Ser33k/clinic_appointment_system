package pl.mikolaj.clinic_appointment_system.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mikolaj.clinic_appointment_system.dto.ScheduleAppointmentDto;
import pl.mikolaj.clinic_appointment_system.entity.Doctor;
import pl.mikolaj.clinic_appointment_system.service.DoctorService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000"}, maxAge = 3600)
@Data
@RestController
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> findAllDoctors(){
        List<Doctor> doctors = doctorService.findAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/doctors/{id}")
    public Optional<Doctor> findDoctorById(@PathVariable(value = "id") Long id){
        return doctorService.findDoctorById(id);
    }

    @GetMapping("/doctors/{licenseNumber}/appointments")
    public List<ScheduleAppointmentDto> findDoctorsAppointments(@PathVariable(value = "licenseNumber") String licenseNumber){
        return doctorService.findDoctorsAppointments(licenseNumber);
    }

    @GetMapping("/licenseNumberByUser/{userIdNumber}")
    public String getLicenseNumberByUser(@PathVariable String userIdNumber){
        return doctorService.getLicenseNumberByUserIdNumber(userIdNumber);
    }
}
