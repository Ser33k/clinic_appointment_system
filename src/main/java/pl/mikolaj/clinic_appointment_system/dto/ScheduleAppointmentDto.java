package pl.mikolaj.clinic_appointment_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ScheduleAppointmentDto {

    private LocalDateTime date;

    private String patientFirstName;

    private String patientLastName;

    private String description;

}
