package pl.mikolaj.clinic_appointment_system.dto;

import lombok.Getter;

@Getter
public class DoctorDto {
    private String idNumber;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    public DoctorDto(String idNumber, String email, String password, String firstName, String lastName, String phoneNumber, String address, String licenseNumber) {
        this.idNumber = idNumber;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.licenseNumber = licenseNumber;
    }

    private String phoneNumber;

    private String address;

    private String licenseNumber;
}
