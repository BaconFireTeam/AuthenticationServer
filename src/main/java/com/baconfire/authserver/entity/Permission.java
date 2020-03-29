package com.baconfire.authserver.entity;

import javax.persistence.*;

@Entity
@Table(name="Permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name="PermissionName")
    private String permissionName;

    @Column(name="PermissionDescription")
    private String permissionDescription;

    @Column(name="CreateDate")
    private String CreateDate;

    @Column(name="ModificationDate")
    private String ModificationDate;

    @Column(name="LastModificationUser")
    private String LastModificationUser;

    public Permission() {
    }

    public Permission(int id, String permissionName, String permissionDescription, String createDate, String modificationDate, String lastModificationUser) {
        this.id = id;
        this.permissionName = permissionName;
        this.permissionDescription = permissionDescription;
        CreateDate = createDate;
        ModificationDate = modificationDate;
        LastModificationUser = lastModificationUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getModificationDate() {
        return ModificationDate;
    }

    public void setModificationDate(String modificationDate) {
        ModificationDate = modificationDate;
    }

    public String getLastModificationuser() {
        return LastModificationUser;
    }

    public void setLastModificationuser(String lastModificationuser) {
        LastModificationUser = lastModificationuser;
    }
}
