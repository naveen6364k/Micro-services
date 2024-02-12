package com.innoclique.patient.details.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 *  @author Naveen Kumar Chintala
 */

@Getter
@Setter
@Entity
@Table(name = "patientdetails")
public class PatientDetails implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PatientID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientID;

    @Column(name = "PatientAccountNo")
    private Integer patientAccountNo;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "EmailAddress")
    private String emailAddress;

    @Column(name = "Address")
    private String address;

    @Column(name = "StateID")
    private Integer stateID;

    @Column(name = "City")
    private String city;

    @Column(name = "Zip")
    private String zip;

    @Column(name = "MobileAppKey")
    private String mobileAppKey;

    @Column(name = "DateCreated")
    private Date dateCreated;

    @Column(name = "Status")
    private Integer status;

}
