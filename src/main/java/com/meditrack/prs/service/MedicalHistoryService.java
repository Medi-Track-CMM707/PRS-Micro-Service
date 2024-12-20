package com.meditrack.prs.service;

import com.meditrack.prs.model.entity.MedicalHistory;
import com.meditrack.prs.repository.MedicalHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalHistoryService {

    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;

    public List<MedicalHistory> getAllMedicalHistories() {
        return medicalHistoryRepository.findAll();
    }

    public MedicalHistory getMedicalHistoryById(Long id) {
        return medicalHistoryRepository.findById(id).orElseThrow(() -> new RuntimeException("MedicalHistory not found"));
    }

    public MedicalHistory addMedicalHistory(MedicalHistory medicalHistory) {
        return medicalHistoryRepository.save(medicalHistory);
    }

    public MedicalHistory updateMedicalHistory(Long id, MedicalHistory medicalHistory) {
        MedicalHistory existingMedicalHistory = getMedicalHistoryById(id);
        existingMedicalHistory.setAppointmentId(medicalHistory.getAppointmentId());
        existingMedicalHistory.setPatientId(medicalHistory.getPatientId());
        existingMedicalHistory.setDiagnosisDate(medicalHistory.getDiagnosisDate());
        existingMedicalHistory.setDiagnosis(medicalHistory.getDiagnosis());
        existingMedicalHistory.setTreatment(medicalHistory.getTreatment());
        existingMedicalHistory.setNotes(medicalHistory.getNotes());
        return medicalHistoryRepository.save(existingMedicalHistory);
    }

    public void deleteMedicalHistory(Long id) {
        medicalHistoryRepository.deleteById(id);
    }
}
