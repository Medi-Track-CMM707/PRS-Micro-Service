package com.meditrack.prs.service;

import com.meditrack.prs.model.entity.Prescription;
import com.meditrack.prs.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public Prescription getPrescriptionById(Long id) {
        return prescriptionRepository.findById(id).orElseThrow(() -> new RuntimeException("Prescription not found"));
    }

    public Prescription addPrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    public Prescription updatePrescription(Long id, Prescription prescription) {
        Prescription existingPrescription = getPrescriptionById(id);
        existingPrescription.setPatientId(prescription.getPatientId());
        existingPrescription.setAppointmentId(prescription.getAppointmentId());
        existingPrescription.setMedicineName(prescription.getMedicineName());
        existingPrescription.setDosage(prescription.getDosage());
        existingPrescription.setFrequency(prescription.getFrequency());
        existingPrescription.setStartDate(prescription.getStartDate());
        existingPrescription.setEndDate(prescription.getEndDate());
        existingPrescription.setDoctorId(prescription.getDoctorId());
        existingPrescription.setNotes(prescription.getNotes());
        return prescriptionRepository.save(existingPrescription);
    }

    public void deletePrescription(Long id) {
        prescriptionRepository.deleteById(id);
    }
}
