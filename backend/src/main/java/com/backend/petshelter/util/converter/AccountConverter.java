package com.backend.petshelter.util.converter;

import com.backend.petshelter.dto.AccountDTO;
import com.backend.petshelter.model.Account;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountConverter {
    public AccountDTO entityToDto(Account customer){
        ModelMapper mapper = new ModelMapper();
        AccountDTO map = mapper.map(customer, AccountDTO.class);
        return map;
    }

    public List<AccountDTO> entityToDto(List<Account> user){
        return user.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }
}
