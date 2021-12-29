package pl.mikolaj.clinic_appointment_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mikolaj.clinic_appointment_system.entity.Patient;
import pl.mikolaj.clinic_appointment_system.entity.User;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findPatientByUser(User user);

    Optional<Patient> findPatientByUserIdNumber(String idNumber);
}
