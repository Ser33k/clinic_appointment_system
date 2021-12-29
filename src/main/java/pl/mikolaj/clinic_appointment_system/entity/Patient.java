package pl.mikolaj.clinic_appointment_system.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patients")

public class Patient{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPatient;

    public Patient(User user, double weight, double height) {
        this.user = user;
        this.weight = weight;
        this.height = height;
    }

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private double weight;

    private double height;

}
