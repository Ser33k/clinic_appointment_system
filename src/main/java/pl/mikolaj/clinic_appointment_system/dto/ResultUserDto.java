package pl.mikolaj.clinic_appointment_system.dto;

import lombok.AllArgsConstructor;
import lombok.Setter;
import pl.mikolaj.clinic_appointment_system.entity.UserRole;

@AllArgsConstructor
@Setter
public class ResultUserDto {
    private UserRole role;

    private String idNumber;
}
