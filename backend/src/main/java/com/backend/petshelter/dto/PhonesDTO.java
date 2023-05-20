package com.backend.petshelter.dto;

import com.backend.petshelter.model.Phones;
import com.backend.petshelter.util.enums.PhoneLabel;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhonesDTO {

    private String phoneUuid;
    private PhoneLabel phoneLabel;
    private String number;
    private String cityCode;
    private String countryCode;
}