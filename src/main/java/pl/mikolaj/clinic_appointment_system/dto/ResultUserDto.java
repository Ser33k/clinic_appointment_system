package pl.mikolaj.clinic_appointment_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.mikolaj.clinic_appointment_system.entity.UserRole;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultUserDto {
    private UserRole role;

    private String idNumber;
}
