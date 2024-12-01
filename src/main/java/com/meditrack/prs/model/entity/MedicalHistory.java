package com.meditrack.prs.model.entity;

import com.meditrack.prs.interceptor.EntityInterceptor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(EntityInterceptor.class)
public class MedicalHistory extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long appointmentId;

    @Column(name = "patient_id")
    private Long patientId;

    private Date diagnosisDate;

    private String diagnosis;

    private String treatment;

    @Column(length = 1000)
    private String notes;
}
