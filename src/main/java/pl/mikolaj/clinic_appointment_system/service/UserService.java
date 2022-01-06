package pl.mikolaj.clinic_appointment_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.mikolaj.clinic_appointment_system.dto.*;
import pl.mikolaj.clinic_appointment_system.entity.*;
import pl.mikolaj.clinic_appointment_system.repository.DoctorRepository;
import pl.mikolaj.clinic_appointment_system.repository.PatientRepository;
import pl.mikolaj.clinic_appointment_system.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AvailabilityDateService availabilityDateService;

    public List<User> findAllUsers() {return userRepository.findAll();}

    public Optional<User> findUserById(Long id) {return userRepository.findById(id);}

    public List<Appointment> findUserAppointments(Long userId){
        return userRepository.findById(userId).get().getAppointmentList();
    }

    public ResultRegistrationDto registration (RegistrationDto registrationDto){
        PatientDto patientDto = registrationDto.getPatientDto();
        DoctorDto doctorDto = registrationDto.getDoctorDto();

        Object [] users = findUser(patientDto, doctorDto);
        if(users[0]==null && patientDto!=null)
            return createPatient(registrationDto);
        if (users[1]==null && doctorDto!=null)
        return createDoctor(registrationDto);

        else return null;
    }

    public User createUser(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        userRepository.save(user);
        return user;
    }

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

    public Object[] findUser(PatientDto patientDto, DoctorDto doctorDto)
    {
        Object[] users={null, null};
        if(patientDto!=null)
        {
            Patient patient = patientRepository.findPatientByUserIdNumber(patientDto.getIdNumber()); //wyszukiwanie wg numeru idNumer, czyli numeru Pesel
            users[0] = patient; }
        if(doctorDto!=null)
        {
            Doctor doctor = doctorRepository.findDoctorByLicenseNumber(doctorDto.getLicenseNumber()); //wyszukiwanie wg numeru licencji
            users[1] = doctor;
        }
        return users;
    }

    private User createUser(RegistrationDto registrationDto,UserRole userRole)
    { BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User(registrationDto.getIdNumber(), registrationDto.getEmail(),
                registrationDto.getPassword(), registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getPhoneNumber(),
                registrationDto.getAddress(), new ArrayList<>(), userRole);
        user.setPassword(encoder.encode(user.getPassword()));
        return user;
    }

    public ResultRegistrationDto createPatient(RegistrationDto registrationDto){

        User user=createUser(registrationDto,UserRole.PATIENT);

        Patient patient = new Patient(user, registrationDto.getPatientDto().getWeight(), registrationDto.getPatientDto().getHeight());

            userRepository.save(user);
            patientRepository.save(patient);
            return new ResultRegistrationDto(registrationDto.getEmail(), registrationDto.getPassword(), registrationDto.getFirstName(), registrationDto.getLastName(), registrationDto.getPhoneNumber(), registrationDto.getAddress());
    }

    public ResultRegistrationDto createDoctor(RegistrationDto registrationDto){
        User user=createUser(registrationDto,UserRole.DOCTOR);
        Doctor doctor = new Doctor(registrationDto.getDoctorDto().getLicenseNumber(),
                user, new ArrayList<>());
        userRepository.save(user);
        doctorRepository.save(doctor);
        pom(doctor);
        return new ResultRegistrationDto(registrationDto.getEmail(),
                registrationDto.getPassword(), registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getPhoneNumber(),
                registrationDto.getAddress());
    }
    private void pom(Doctor doctor) {
        AvailabilityDate ad = new AvailabilityDate(LocalDateTime.parse("2022-01-30T20:30"), true, 30, doctor);
        AvailabilityDate ad1 = new AvailabilityDate(LocalDateTime.parse("2022-01-30T19:00"), true, 30, doctor);
        AvailabilityDate ad2 = new AvailabilityDate(LocalDateTime.parse("2020-01-30T18:30"), true, 30, doctor);
        availabilityDateService.createAvailabilityDate(ad);
        availabilityDateService.createAvailabilityDate(ad1);
        availabilityDateService.createAvailabilityDate(ad2);
    }
}
