package pl.mikolaj.clinic_appointment_system.controller;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/users")
    public List<User> findAllUsers(){return userService.findAllUsers();}

    @GetMapping("users/{id}")
    public ResponseEntity<User> findUserById(
            @PathVariable(value = "id") Long userId
    ) throws  ResourceNotFoundException {
        User user = userService.findUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found ::" + userId));
        return ResponseEntity.ok().body(user);
    }

    @CrossOrigin(origins = {"http://localhost:3000"}, maxAge = 3600)
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody UserLoginDto loginDto){
        int a = 0;
        return userService.loginUser(loginDto);
    }

    @GetMapping("users/{id}/appointments")
    public ResponseEntity<List<Appointment>> findUserAppointments(@PathVariable(value = "id") Long userId) {
        List<Appointment> appointments = userService.findUserAppointments(userId);

        return ResponseEntity.ok().body(appointments);
    }

//    @PostMapping("/users")
//    public ResponseEntity<User> loginUser(@RequestBody LoginForm loginForm){
//        User user = null;
//        boolean log = false;
//        try {
//            user = userService.findUserByEmail(loginForm.getEmail());
//
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//        if (user != null){
////            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
////
////            log = encoder.matches(loginForm.getPassword(), user.getPassword());
//            if (log){
//                return new ResponseEntity<User>(user, HttpStatus.OK);
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//            }
//
//        }else {
//            return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
//        }
//
//    }

    @PostMapping("/addUser")
    public User addUser (@RequestBody User user){
        return userService.createUser(user);
    }
}
