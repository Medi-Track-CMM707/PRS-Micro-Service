package com.meditrack.prs.controller;

import com.meditrack.prs.model.entity.MedicalHistory;
import com.meditrack.prs.service.MedicalHistoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "MedicalHistory", description = "The Medical History API")
@Slf4j
@RequestMapping("/medicalHistories")
@Validated
public class MedicalHistoryController {

    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<MedicalHistory>> getAllMedicalHistories() {
        log.info("Get all medical histories");
        List<MedicalHistory> medicalHistories = medicalHistoryService.getAllMedicalHistories();
        return ResponseEntity.ok(medicalHistories);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<MedicalHistory> getMedicalHistoryById(@PathVariable Long id) {
        log.info("Get medical history by ID: {}", id);
        MedicalHistory medicalHistory = medicalHistoryService.getMedicalHistoryById(id);
        return ResponseEntity.ok(medicalHistory);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<MedicalHistory> addMedicalHistory(@RequestBody MedicalHistory medicalHistory) {
        log.info("Add new medical history");
        MedicalHistory newMedicalHistory = medicalHistoryService.addMedicalHistory(medicalHistory);
        return ResponseEntity.ok(newMedicalHistory);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MedicalHistory> updateMedicalHistory(@PathVariable Long id, @RequestBody MedicalHistory medicalHistory) {
        log.info("Update medical history with ID: {}", id);
        MedicalHistory updatedMedicalHistory = medicalHistoryService.updateMedicalHistory(id, medicalHistory);
        return ResponseEntity.ok(updatedMedicalHistory);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteMedicalHistory(@PathVariable Long id) {
        log.info("Delete medical history with ID: {}", id);
        medicalHistoryService.deleteMedicalHistory(id);
        return ResponseEntity.noContent().build();
    }
}
