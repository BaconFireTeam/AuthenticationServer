package com.baconfire.authserver.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "RegistrationToken")
public class RegistrationToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "Token")
    private String token;

    @Column(name = "ValidDuration")
    private String validDuration;

    @Column(name = "Email")
    private String email;

    @Column(name = "CreatedBy")
    private  String createdBy;

}
