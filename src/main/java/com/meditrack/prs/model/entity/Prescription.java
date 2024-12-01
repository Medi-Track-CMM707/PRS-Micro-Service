package com.meditrack.prs.model.entity;

import com.meditrack.prs.interceptor.EntityInterceptor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(EntityInterceptor.class)
public class Prescription extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "patient_id")
    private Long patientId;

    private Long appointmentId;

    private String medicineName;

    private String dosage;

    private String frequency;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    private Long doctorId;

    @Column(length = 1000)
    private String notes;

}
