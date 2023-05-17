package com.backend.petshelter.model;

import com.backend.petshelter.util.dateformat.DatePattern;
import com.backend.petshelter.util.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "ACCOUNT")
public class Account {
    @Id
    private String uuid;
    @Column(name = "email", nullable = false)
    @Email(message = "Please enter a Valid email!")
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private Role rol;
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;
    @Column(name = "last_session_date", nullable = false)
    private LocalDateTime LastSessionDate;
    @Column(name = "active")
    private boolean active;
    @Transient
    private String token;

    public Account(String email, String password) {
        this.uuid = UUID.randomUUID().toString();
        this.email = email;
        this.password = password;
        this.rol = Role.USER;
        this.createdDate = DatePattern.getCurrentDateTimeFormatted();
        this.LastSessionDate = DatePattern.getCurrentDateTimeFormatted();
        this.active = true;
    }
}
