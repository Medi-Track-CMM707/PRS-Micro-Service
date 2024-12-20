package com.meditrack.prs.controller;

import com.meditrack.prs.model.entity.Prescription;
import com.meditrack.prs.service.PrescriptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Prescription", description = "The Prescription API")
@Slf4j
@RequestMapping("/prescriptions")
@Validated
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
        log.info("Get all prescriptions");
        List<Prescription> prescriptions = prescriptionService.getAllPrescriptions();
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable Long id) {
        log.info("Get prescription by ID: {}", id);
        Prescription prescription = prescriptionService.getPrescriptionById(id);
        return ResponseEntity.ok(prescription);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Prescription> addPrescription(@RequestBody Prescription prescription) {
        log.info("Add new prescription");
        Prescription newPrescription = prescriptionService.addPrescription(prescription);
        return ResponseEntity.ok(newPrescription);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Prescription> updatePrescription(@PathVariable Long id, @RequestBody Prescription prescription) {
        log.info("Update prescription with ID: {}", id);
        Prescription updatedPrescription = prescriptionService.updatePrescription(id, prescription);
        return ResponseEntity.ok(updatedPrescription);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable Long id) {
        log.info("Delete prescription with ID: {}", id);
        prescriptionService.deletePrescription(id);
        return ResponseEntity.noContent().build();
    }
}
