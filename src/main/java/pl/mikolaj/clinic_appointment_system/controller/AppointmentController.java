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

    @PostMapping("/appointment")
    public ResultAppointmentDto createAppointment(@RequestBody ReserveAppointmentDto reserveAppointmentDto){
        return appointmentService.reserveAppointment(reserveAppointmentDto);
    }

}
