package pl.mikolaj.clinic_appointment_system.dto;

import lombok.Getter;
import pl.mikolaj.clinic_appointment_system.entity.UserRole;

@Getter
public class PatientDto {
    private String idNumber;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String address;

    public PatientDto(String idNumber, String email, String password, String firstName, String lastName, String phoneNumber, String address) {
        this.idNumber = idNumber;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
