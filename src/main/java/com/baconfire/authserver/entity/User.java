package com.baconfire.authserver.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="User")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name="UserName")
    private String username;

    @Column(name="Email")
    private String email;

    @Column(name="Password")
    private String password;

//    @Column(name="PersonID")
//    private int personID;
    @Column(name = "Active")
    private String active;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PersonID")
    private Person person;

    @Column(name="CreateDate")
    private String createDate;

    @Column(name="ModificationDate")
    private String modificationDate;

    public User(String username, String email, String password, Person person, String createDate, String modificationDate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.person = person;
        this.createDate = createDate;
        this.modificationDate = modificationDate;
    }
//    public User() {
//    }
//
//    public User(int ID, String userName, String email, String password, int personID, String createDate, String modificationDate) {
//        this.ID = ID;
//        UserName = userName;
//        Email = email;
//        Password = password;
//        PersonID = personID;
//        CreateDate = createDate;
//        ModificationDate = modificationDate;
//    }
//
//    public int getID() {
//        return ID;
//    }
//
//    public void setID(int ID) {
//        this.ID = ID;
//    }
//
//    public String getUserName() {
//        return UserName;
//    }
//
//    public void setUserName(String userName) {
//        UserName = userName;
//    }
//
//    public String getEmail() {
//        return Email;
//    }
//
//    public void setEmail(String email) {
//        Email = email;
//    }
//
//    public String getPassword() {
//        return Password;
//    }
//
//    public void setPassword(String password) {
//        Password = password;
//    }
//
//    public int getPersonID() {
//        return PersonID;
//    }
//
//    public void setPersonID(int personID) {
//        PersonID = personID;
//    }
//
//    public String getCreateDate() {
//        return CreateDate;
//    }
//
//    public void setCreateDate(String createDate) {
//        CreateDate = createDate;
//    }
//
//    public String getModificationDate() {
//        return ModificationDate;
//    }
//
//    public void setModificationDate(String modificationDate) {
//        ModificationDate = modificationDate;
//    }
}
