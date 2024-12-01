package com.meditrack.prs.model.entity;

import com.meditrack.prs.constant.GENDER;
import com.meditrack.prs.interceptor.EntityInterceptor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(EntityInterceptor.class)
public class Patient extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String address;

    @Temporal(TemporalType.DATE)
    private Date dob;

    @Enumerated(EnumType.STRING)
    private GENDER gender;

    @Column(length = 10)
    private Long contactNumber;

    @Column(length = 10)
    private Long emergencyContactNumber;

    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private List<LabResult> labResults;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private List<MedicalHistory> medicalHistories;

    @Transient
    private int age;

    @PostLoad
    public void postLoad() {
        if (dob != null) {
            // Calculate age
            this.age = calculateAge(dob);
        }
    }

    private int calculateAge(Date dob) {
        if (dob != null) {
            // Convert java.util.Date to java.time.LocalDate
            LocalDate dobLocalDate = dob.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            LocalDate currentDate = LocalDate.now();
            if (dobLocalDate.isAfter(currentDate)) {
                throw new IllegalArgumentException("Date of birth cannot be in the future");
            }
            return Period.between(dobLocalDate, currentDate).getYears();
        }
        return 0;
    }
}
