package com.meditrack.prs.controller;

import com.meditrack.prs.model.entity.Patient;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Patient", description = "The Patient API")
@Slf4j
@RequestMapping("/patient")
@Validated
public class PatientController {

    @GetMapping(produces = "application/json")
    public ResponseEntity<Patient> getPatient() {
        log.info("Get Patient");
        return ResponseEntity.ok(new Patient());
    }
}
