package pl.mikolaj.clinic_appointment_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mikolaj.clinic_appointment_system.entity.Appointment;
import pl.mikolaj.clinic_appointment_system.entity.AvailabilityDate;
import pl.mikolaj.clinic_appointment_system.entity.Doctor;
import pl.mikolaj.clinic_appointment_system.repository.AvailabilityDateRepository;
import pl.mikolaj.clinic_appointment_system.repository.DoctorRepository;

import javax.print.Doc;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityDateService {
    @Autowired
    private AvailabilityDateRepository availabilityDateRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public AvailabilityDate createAvailabilityDate(AvailabilityDate availabilityDate){

        Doctor doctor = availabilityDate.getDoctor();

//        Optional<AvailabilityDate> adOptional = doctor.getAvailabilityDates().stream().filter(a -> a.getDate().isEqual(availabilityDate.getDate())).findFirst();

//        if (adOptional.isEmpty()){
            availabilityDateRepository.save(availabilityDate);

            doctor.getAvailabilityDates().add(availabilityDate);
            doctorRepository.save(doctor);
//        }

        return availabilityDate;
    }

    public List<AvailabilityDate> findAvailabilityDateByDoctor(Long idDoctor){

        Optional<Doctor> doctorOptional = doctorRepository.findById(idDoctor);

        return doctorOptional.map(Doctor::getAvailabilityDates).orElse(null);

    }
}
