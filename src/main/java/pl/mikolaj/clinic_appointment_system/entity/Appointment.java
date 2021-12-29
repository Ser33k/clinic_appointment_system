package pl.mikolaj.clinic_appointment_system.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appointmentId;

    public Appointment(AvailabilityDate appointmentDate, AppointmentStatus status, String description, Patient patient) {
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.description = description;
        this.patient = patient;
    }

    @OneToOne
    @JoinColumn(name = "appointmentdate_id")
    private AvailabilityDate appointmentDate;

    private AppointmentStatus status;

    private String description;

    @ManyToOne
    private Patient patient;

}
