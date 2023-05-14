package com.nocountry.petshelter.model.account;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Users")
public class User {

    @Id
    @Column(name = "userid")
    private String userId;
    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String userName;
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Please enter a Valid email!")
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private Rol rol;

    @Column(name = "created date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    transient
    private String token;

    @Column(name = "active")
    private boolean active;
}
