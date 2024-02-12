package com.innoclique.patient.details.controller;

import com.innoclique.patient.details.entity.PatientDetails;
import com.innoclique.patient.details.service.PatientDetailsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Naveen Kumar Chintala
 */
@RestController
@RequestMapping("/patientDetails")
@Log4j2
public class PatientDetailsContoller {

    @Autowired
    private PatientDetailsService patientDetailsService;


    @PostMapping("/savePatient")
    public ResponseEntity<?> save(@Validated @RequestBody PatientDetails patientDetails) {

        patientDetails =  patientDetailsService.save(patientDetails);
        return new ResponseEntity<>(patientDetails, HttpStatus.OK);
    }

    @GetMapping("/getPatientDetails/{id}")
    public ResponseEntity<?> getPatientDetails(@PathVariable Integer id){
        log.info("Getting patient details for Id : {}", id);
       PatientDetails patientDetails =  patientDetailsService.getById(id);
        log.info("Patient details are : {}", patientDetails);

        return new ResponseEntity<>(patientDetails, HttpStatus.OK);
    }



}

