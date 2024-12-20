package com.meditrack.prs.controller;

import com.meditrack.prs.model.entity.Patient;
import com.meditrack.prs.service.PatientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Patient", description = "The Patient API")
@Slf4j
@RequestMapping("/patients")
@Validated
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Patient>> getAllPatients() {
        log.info("Get all patients");
        List<Patient> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        log.info("Get patient by ID: {}", id);
        Patient patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        log.info("Add new patient");
        Patient newPatient = patientService.addPatient(patient);
        return ResponseEntity.ok(newPatient);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        log.info("Update patient with ID: {}", id);
        Patient updatedPatient = patientService.updatePatient(id, patient);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        log.info("Delete patient with ID: {}", id);
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}







// package com.meditrack.prs.controller;

// import com.meditrack.prs.model.entity.Patient;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @Tag(name = "Patient", description = "The Patient API")
// @Slf4j
// @RequestMapping("/patient")
// @Validated
// public class PatientController {

//     @GetMapping(produces = "application/json")
//     public ResponseEntity<Patient> getPatient() {
//         log.info("Get Patient");
//         return ResponseEntity.ok(new Patient());
//     }
// }
