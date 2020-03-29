package com.baconfire.onboardwebapp.security.springsecurityjwt.domain;
import javax.persistence.*;

@Entity
@Table(name="Role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name="RoleName")
    private String roleName;

    @Column(name="Description")
    private String description;

    @Column(name="CreateDate")
    private String createDate;

    @Column(name="ModificationDate")
    private String modificationDate;

    @Column(name="LastModificationUser")
    private String lastModificationUser;

    public Role() {
    }

    public Role(int id, String roleName, String description, String createDate, String modificationDate, String lastModificationUser) {
        this.id = id;
        this.roleName = roleName;
        this.description = description;
        this.createDate = createDate;
        this.modificationDate = modificationDate;
        this.lastModificationUser = lastModificationUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getLastModificationUser() {
        return lastModificationUser;
    }

    public void setLastModificationUser(String lastModificationUser) {
        this.lastModificationUser = lastModificationUser;
    }
}
