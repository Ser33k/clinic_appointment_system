package pl.mikolaj.clinic_appointment_system.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.mikolaj.clinic_appointment_system.entity.AvailabilityDate;
import pl.mikolaj.clinic_appointment_system.entity.User;
import pl.mikolaj.clinic_appointment_system.service.AvailabilityDateService;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"}, maxAge = 3600)
@Data
@RestController
public class AvailabilityDateController {

    private final AvailabilityDateService availabilityDateService;

    @GetMapping("doctors/{id}/availability")
    public ResponseEntity<List<AvailabilityDate>> findAvailabilityDatesByDoctor(
            @PathVariable(value = "id") Long doctorId
    ) {
        List<AvailabilityDate> availabilityDates = availabilityDateService.findAvailabilityDateByDoctor(doctorId);

        return ResponseEntity.ok(availabilityDates);
    }
}
