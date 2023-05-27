package com.backend.petshelter.model;

import com.backend.petshelter.util.enums.IdentificationType;
import com.backend.petshelter.util.enums.Sex;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "ACCOUNTDETAILS")
public class AccountDetails implements Serializable {
    @Id
    @Column(name = "accountdetails_uuid")
    private String accountDetailsuuid;
    private String fullName;
    @Enumerated(EnumType.STRING)
    @Column(name = "identification_type")
    private IdentificationType identificationType;
    @Column(name = "identification", unique = true)
    private String identification;
    @Column(name = "city")
    private String city;
    @Column(name = "address")
    private String address;
    @OneToOne
    @JoinColumn(name = "accountUuid", referencedColumnName = "account_uuid")
    private Account account;
    @OneToMany(targetEntity = Phones.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="accountdetails_uuid",referencedColumnName = "accountdetails_uuid", nullable = false)
    private List<Phones> phonesList;
    @Enumerated(EnumType.STRING)
    @Column(name = "sex", length = 8)
    private Sex sex;
    private Date dateBirth;


}
