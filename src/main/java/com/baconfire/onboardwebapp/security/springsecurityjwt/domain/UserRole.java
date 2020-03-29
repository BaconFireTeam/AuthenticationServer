package com.baconfire.onboardwebapp.security.springsecurityjwt.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor


@Entity
@Table(name="UserRole")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name="UserID")
    private int userId;

    @Column(name="RoleID")
    private int roleId;

    @Column(name="ActiveFlag")
    private int activeFlag;

    @Column(name="CreateDate")
    private String createDate;

    @Column(name="ModificationDate")
    private String modificationDate;

    @Column(name="LastModificationUser")
    private String lastModificationUser;
}
