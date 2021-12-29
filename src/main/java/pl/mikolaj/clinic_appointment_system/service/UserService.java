package pl.mikolaj.clinic_appointment_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.mikolaj.clinic_appointment_system.dto.UserLoginDto;
import pl.mikolaj.clinic_appointment_system.entity.Appointment;
import pl.mikolaj.clinic_appointment_system.entity.User;
import pl.mikolaj.clinic_appointment_system.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    public List<User> findAllUsers() {return userRepository.findAll();}

    public Optional<User> findUserById(Long id) {return userRepository.findById(id);}

    public List<Appointment> findUserAppointments(Long userId){return userRepository.findById(userId).get().getAppointmentList();}
    public User addUser(User user) {return userRepository.save(user);}

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User createUser(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        userRepository.save(user);
        return user;
    }
//
    public User findUserByEmail(String email) {return userRepository.findUserByEmail(email);}

    public ResponseEntity<User> loginUser(@RequestBody UserLoginDto loginForm){
        User user = null;
        boolean log = false;
        try {
            user = findUserByEmail(loginForm.getEmail());

        } catch (Exception e){
            e.printStackTrace();
        }

        if (user != null){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
            log = encoder.matches(loginForm.getPassword(), user.getPassword());
            if (log){
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

        }else {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }

    }
}
