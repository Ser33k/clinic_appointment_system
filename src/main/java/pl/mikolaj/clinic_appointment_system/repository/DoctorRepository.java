package pl.mikolaj.clinic_appointment_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mikolaj.clinic_appointment_system.entity.Doctor;
import pl.mikolaj.clinic_appointment_system.entity.User;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findDoctorByUser(User user);
}
