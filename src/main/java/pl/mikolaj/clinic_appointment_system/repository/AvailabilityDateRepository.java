package pl.mikolaj.clinic_appointment_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mikolaj.clinic_appointment_system.entity.AvailabilityDate;
import pl.mikolaj.clinic_appointment_system.entity.Doctor;

import java.util.List;

@Repository
public interface AvailabilityDateRepository extends JpaRepository<AvailabilityDate, Long> {
    List<AvailabilityDate> findAvailabilityDateByDoctor(Doctor doctor);
}
