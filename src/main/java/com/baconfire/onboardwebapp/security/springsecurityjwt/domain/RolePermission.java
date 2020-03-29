package com.baconfire.onboardwebapp.security.springsecurityjwt.domain;

import javax.persistence.*;

@Entity
@Table(name="RolePermission")
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name="RoleID")
    private int roleId;

    @Column(name="PermissionID")
    private int permissionId;

    @Column(name="ActiveFlag")
    private int activeFlag;

    @Column(name="CreateDate")
    private String createDate;

    @Column(name="ModificationDate")
    private String modificationDate;

    @Column(name="LastModificationUser")
    private String lastModificationUser;

    public RolePermission() {
    }

    public RolePermission(int id, int roleId, int permissionId, int activeFlag, String createDate, String modificationDate, String lastModificationUser) {
        this.id = id;
        this.roleId = roleId;
        this.permissionId = permissionId;
        this.activeFlag = activeFlag;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public int getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(int activeFlag) {
        this.activeFlag = activeFlag;
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
