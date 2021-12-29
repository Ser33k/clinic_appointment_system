package pl.mikolaj.clinic_appointment_system.dto;

import lombok.Getter;

@Getter
public class UserLoginDto {
    private String email;
    private String password;
}
