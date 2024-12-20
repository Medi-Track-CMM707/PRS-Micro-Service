package com.meditrack.prs.service;

import com.meditrack.prs.model.entity.LabResult;
import com.meditrack.prs.repository.LabResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabResultService {

    @Autowired
    private LabResultRepository labResultRepository;

    public List<LabResult> getAllLabResults() {
        return labResultRepository.findAll();
    }

    public LabResult getLabResultById(Long id) {
        return labResultRepository.findById(id).orElseThrow(() -> new RuntimeException("LabResult not found"));
    }

    public LabResult addLabResult(LabResult labResult) {
        return labResultRepository.save(labResult);
    }

    public LabResult updateLabResult(Long id, LabResult labResult) {
        LabResult existingLabResult = getLabResultById(id);
        existingLabResult.setPatientId(labResult.getPatientId());
        existingLabResult.setAppointmentId(labResult.getAppointmentId());
        existingLabResult.setTestName(labResult.getTestName());
        existingLabResult.setTestDate(labResult.getTestDate());
        existingLabResult.setResult(labResult.getResult());
        existingLabResult.setNotes(labResult.getNotes());
        existingLabResult.setConductedBy(labResult.getConductedBy());
        return labResultRepository.save(existingLabResult);
    }

    public void deleteLabResult(Long id) {
        labResultRepository.deleteById(id);
    }
}
