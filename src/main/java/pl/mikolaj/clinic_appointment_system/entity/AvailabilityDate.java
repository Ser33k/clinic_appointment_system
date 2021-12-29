package pl.mikolaj.clinic_appointment_system.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "availability_dates")

public class AvailabilityDate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAvailabilityDate;

    private LocalDateTime date;

    private boolean isFree;

    private int durationMinutes;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public AvailabilityDate(LocalDateTime date, boolean isFree, int durationMinutes, Doctor doctor) {
        this.date = date;
        this.isFree = isFree;
        this.durationMinutes = durationMinutes;
        this.doctor = doctor;
    }
}
