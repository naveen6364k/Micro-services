package com.innoclique.patient.details.service;

import com.innoclique.patient.details.entity.PatientDetails;
import com.innoclique.patient.details.repositary.PatientDetailsRepositary;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class PatientDetailsService {

    @Autowired
    private PatientDetailsRepositary patientDetailsRepositary;

    public PatientDetails save(PatientDetails patientDetails) {
        patientDetails = patientDetailsRepositary.save(patientDetails);
        return patientDetails;
    }

    public PatientDetails getById(Integer id) {
        log.info("Getting patient details for Id : {}", id);
        PatientDetails patientDetails = patientDetailsRepositary.findByPatientID(id);
        log.info(" Patient details are : {}", patientDetails);
        return patientDetails;
    }
}
