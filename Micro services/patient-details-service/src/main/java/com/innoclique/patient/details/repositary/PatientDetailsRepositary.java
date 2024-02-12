package com.innoclique.patient.details.repositary;

import com.innoclique.patient.details.entity.PatientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface PatientDetailsRepositary extends JpaRepository<PatientDetails, Serializable> {

    PatientDetails findByPatientID(Integer id);
}
