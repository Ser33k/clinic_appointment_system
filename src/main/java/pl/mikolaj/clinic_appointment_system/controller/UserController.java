package pl.mikolaj.clinic_appointment_system.controller;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mikolaj.clinic_appointment_system.dto.RegistrationDto;
import pl.mikolaj.clinic_appointment_system.dto.ResultRegistrationDto;
import pl.mikolaj.clinic_appointment_system.dto.UserLoginDto;
import pl.mikolaj.clinic_appointment_system.entity.Appointment;
import pl.mikolaj.clinic_appointment_system.entity.User;
import pl.mikolaj.clinic_appointment_system.service.UserService;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"}, maxAge = 3600)
@RestController
@Data
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    public ResultRegistrationDto registration (@RequestBody RegistrationDto registrationDto){
        return userService.registration(registrationDto);
    }

    @CrossOrigin(origins = {"http://localhost:3000"}, maxAge = 3600)
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody UserLoginDto loginDto){
        return userService.loginUser(loginDto);
    }
}
