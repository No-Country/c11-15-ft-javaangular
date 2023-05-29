package com.backend.petshelter.model;

import com.backend.petshelter.util.format.DatePattern;
import com.backend.petshelter.util.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable {
    @Id
    @Column(name = "account_uuid")
    private String accountUuid;
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Please enter a Valid email!")
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private Role rol;
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;
    @Column(name = "last_session_date", nullable = false)
    private LocalDateTime LastSessionDate;
    @Column(name = "active")
    private boolean active;
    @Column(name = "verification_code", updatable = false)
    private String verificationCode;
    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private AccountDetails accountDetails;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<WishList> wishList;
    @Transient
    private String token;

    public Account(String email, String password, String verificationCode) {
        this.accountUuid= UUID.randomUUID().toString();
        this.email = email;
        this.password = password;
        this.verificationCode = verificationCode;
        this.rol = Role.USER;
        this.createdDate = DatePattern.getCurrentDateTimeFormatted();
        this.LastSessionDate = DatePattern.getCurrentDateTimeFormatted();
        this.active = false;
    }
}
