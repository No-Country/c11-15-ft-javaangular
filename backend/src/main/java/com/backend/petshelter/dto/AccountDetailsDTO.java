package com.backend.petshelter.dto;

import com.backend.petshelter.model.AccountDetails;
import com.backend.petshelter.util.enums.IdentificationType;
import com.backend.petshelter.util.enums.Sex;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailsDTO {
    private String fullName;
    private IdentificationType identificationType;
    private String identification;
    private String city;
    private String address;
    private List<PhonesDTO> phonesList;
    private Sex sex;
    private Date dateBirth;
}