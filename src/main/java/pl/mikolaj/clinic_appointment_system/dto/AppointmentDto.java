package pl.mikolaj.clinic_appointment_system.dto;

import lombok.Data;
import lombok.Getter;
import pl.mikolaj.clinic_appointment_system.entity.AvailabilityDate;
import pl.mikolaj.clinic_appointment_system.entity.Patient;
import pl.mikolaj.clinic_appointment_system.entity.User;

@Data
@Getter
public class AppointmentDto {

    private AvailabilityDate appointmentDate;

    private String description;

    private User user;
}
