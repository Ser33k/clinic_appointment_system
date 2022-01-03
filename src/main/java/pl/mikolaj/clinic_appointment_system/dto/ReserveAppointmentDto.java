package pl.mikolaj.clinic_appointment_system.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReserveAppointmentDto {

    private PatientDto patientDto;

    private DoctorDto doctorDto;

    private LocalDateTime date;

    private int duration;

    private String description;
}
