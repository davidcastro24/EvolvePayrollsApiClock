package com.davcode.clockapp.models;

import com.davcode.clockapp.roles.Role;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "User")
@Data
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String userName;
    private String password;
    private Role role;
    private boolean isActive;
    private Date creationDate;
    private boolean changePasswordOnNextLogin;
    private Date suspensionDate;
    private boolean emailVerified;
}
