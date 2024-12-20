package com.meditrack.prs.service;

import com.meditrack.prs.model.entity.Patient;
import com.meditrack.prs.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient patient) {
        Patient existingPatient = getPatientById(id);
        existingPatient.setName(patient.getName());
        existingPatient.setAddress(patient.getAddress());
        existingPatient.setDob(patient.getDob());
        existingPatient.setGender(patient.getGender());
        existingPatient.setContactNumber(patient.getContactNumber());
        existingPatient.setEmergencyContactNumber(patient.getEmergencyContactNumber());
        existingPatient.setEmail(patient.getEmail());
        existingPatient.setLabResults(patient.getLabResults());
        existingPatient.setMedicalHistories(patient.getMedicalHistories());
        return patientRepository.save(existingPatient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
