package com.meditrack.prs.controller;

import com.meditrack.prs.model.entity.LabResult;
import com.meditrack.prs.service.LabResultService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "LabResult", description = "The Lab Result API")
@Slf4j
@RequestMapping("/labResults")
@Validated
public class LabResultController {

    @Autowired
    private LabResultService labResultService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<LabResult>> getAllLabResults() {
        log.info("Get all lab results");
        List<LabResult> labResults = labResultService.getAllLabResults();
        return ResponseEntity.ok(labResults);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<LabResult> getLabResultById(@PathVariable Long id) {
        log.info("Get lab result by ID: {}", id);
        LabResult labResult = labResultService.getLabResultById(id);
        return ResponseEntity.ok(labResult);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<LabResult> addLabResult(@RequestBody LabResult labResult) {
        log.info("Add new lab result");
        LabResult newLabResult = labResultService.addLabResult(labResult);
        return ResponseEntity.ok(newLabResult);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<LabResult> updateLabResult(@PathVariable Long id, @RequestBody LabResult labResult) {
        log.info("Update lab result with ID: {}", id);
        LabResult updatedLabResult = labResultService.updateLabResult(id, labResult);
        return ResponseEntity.ok(updatedLabResult);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteLabResult(@PathVariable Long id) {
        log.info("Delete lab result with ID: {}", id);
        labResultService.deleteLabResult(id);
        return ResponseEntity.noContent().build();
    }
}
