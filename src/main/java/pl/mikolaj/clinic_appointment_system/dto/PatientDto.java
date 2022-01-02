package pl.mikolaj.clinic_appointment_system.dto;

import lombok.Getter;
import pl.mikolaj.clinic_appointment_system.entity.UserRole;

@Getter
public class PatientDto {
    private String idNumber;

    private double weight;

    private double height;
}
