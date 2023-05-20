package com.backend.petshelter.model;

import com.backend.petshelter.util.enums.PhoneLabel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "PHONE")
public class Phones {
    @Id
    private String phoneUuid;
    @Enumerated(EnumType.STRING)
    private PhoneLabel phoneLabel;
    @Column(name = "number")
    private String number;
    @Column(name = "city_code")
    private String cityCode;
    @Column(name = "country_code")
    private String countryCode;
}
