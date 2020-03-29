package com.baconfire.onboardwebapp.security.springsecurityjwt.domain;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Person")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name="FirstName")
    private String firstname;

    @Column(name="LastName")
    private String lastname;

    @Column(name="MiddleName")
    private String middlename;

    @Column(name="Email")
    private String email;

    @Column(name="CellPhone")
    private String cellphone;

    @Column(name="AlternatePhone")
    private String alternatephone;

    @Column(name="Gender")
    private String gender;

    @Column(name="SSN")
    private String SSN;

    @Column(name="DOB")
    private String DOB;


    public Person(String firstName, String lastName, String middleName, String email, String cellPhone, String alternatePhone, String gender, String ssn, String dob) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.middlename = middleName;
        this.email = email;
        this.cellphone = cellPhone;
        this.alternatephone = alternatePhone;
        this.gender = gender;
        this.SSN = ssn;
        this.DOB = dob;
    }
}
