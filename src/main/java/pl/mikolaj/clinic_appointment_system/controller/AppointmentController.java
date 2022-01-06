package pl.mikolaj.clinic_appointment_system.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mikolaj.clinic_appointment_system.dto.AppointmentDto;
import pl.mikolaj.clinic_appointment_system.dto.ReserveAppointmentDto;
import pl.mikolaj.clinic_appointment_system.dto.ResultAppointmentDto;
import pl.mikolaj.clinic_appointment_system.entity.Appointment;
import pl.mikolaj.clinic_appointment_system.entity.AppointmentStatus;
import pl.mikolaj.clinic_appointment_system.entity.Doctor;
import pl.mikolaj.clinic_appointment_system.service.AppointmentService;
import pl.mikolaj.clinic_appointment_system.service.DoctorService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000"}, maxAge = 3600)
@Data
@RestController
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping("/appointments")
    public List<Appointment> findAllAppointment(){
        return appointmentService.findAllAppointments();
    }

    @GetMapping("/appointments/{id}")
    public Optional<Appointment> findAppointmentById(@PathVariable(value = "id") Long id){
        return appointmentService.findAppointmentById(id);
    }

    @PostMapping("/appointment")
    public ResultAppointmentDto createAppointment(@RequestBody ReserveAppointmentDto reserveAppointmentDto){
        return appointmentService.reserveAppointment(reserveAppointmentDto);
    }

    @GetMapping("/appointments/{status}")
    public List<Appointment> findAppointmentByStatus(@PathVariable(value = "status") AppointmentStatus status){
        return appointmentService.findAppointmentsByStatus(status);
    }


}
