package com.backend.petshelter.service.implementation;

import com.backend.petshelter.dto.AccountDTO;
import com.backend.petshelter.dto.AccountDetailsDTO;
import com.backend.petshelter.dto.AccountRegistration;
import com.backend.petshelter.dto.PhonesDTO;
import com.backend.petshelter.model.Account;
import com.backend.petshelter.model.AccountDetails;
import com.backend.petshelter.model.Phones;
import com.backend.petshelter.repository.AccountRepository;
import com.backend.petshelter.security.jwt.JwtProvider;
import com.backend.petshelter.service.AccountService;
import com.backend.petshelter.util.enums.IdentificationType;
import com.backend.petshelter.util.enums.PhoneLabel;
import com.backend.petshelter.util.enums.Role;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String emailFrom;

    @Override
    public AccountRegistration createAccountUserRol(Account account) {

        try {

            AccountRegistration accountRegistration = new AccountRegistration();
            String email = account.getEmail();
            accountRegistration.setEmail(email);
            String password = passwordEncoder.encode(account.getPassword());
            accountRegistration.setPassword(passwordEncoder.encode(password));
            String verificationCode = RandomString.make(64);
            accountRegistration.setVerificationCode(verificationCode);

            Account saveAccount = new Account(email, password, verificationCode);

            AccountDetails accountDetails = new AccountDetails();
            accountDetails.setAccountDetailsuuid(UUID.randomUUID().toString());
            accountDetails.setAccount(saveAccount);
            accountDetails.setFullName(account.getAccountDetails().getFullName());
            saveAccount.setAccountDetails(accountDetails);
            accountRegistration.setFullName(account.getAccountDetails());

            accountRepository.save(saveAccount);
            String jwt = jwtProvider.generateToken(saveAccount);
            accountRegistration.setToken(jwt);
            this.sendVerificationCodeToEmail(saveAccount);
            return accountRegistration;

        } catch (Exception e) {
            throw new RuntimeException("Error creating account: " + e.getMessage());
        }
    }
    @Override
    public void sendVerificationCodeToEmail(Account account) throws MessagingException, UnsupportedEncodingException {


        String subject = "Please verify your registration";
        String senderName = "Mascota en Casa Team";
        String mailContent = "<head>";
        mailContent += "<style>";
        mailContent += "a{";
        mailContent += "display: block;";
        mailContent += "width: 200px;";
        mailContent += "font-family: Arial, Helvetica, sans-serif;";
        mailContent += "font-weight: 700;";
        mailContent += "color: #FFB344;";
        mailContent += "background-color: #00A19D;";
        mailContent += "border-radius: 10px;";
        mailContent += "padding: 15px 30px;";
        mailContent += "margin: 20px 20px;";
        mailContent += "text-align: center;";
        mailContent += "text-decoration: none;";
        mailContent += "}";
        mailContent += "a:hover{";
        mailContent += "background-color: #FFB344;";
        mailContent += "border: 2px solid #00A19D;";
        mailContent += "color: #00A19D;";
        mailContent += "}";
        mailContent += "</style>";
        mailContent += "</head>";
        mailContent += "<p>Dear " + account.getAccountDetails().getFullName() + ",</p>";
        mailContent += "<p> Please click the link below to verify to your registration:</p>";

        String verifyURL = "/verify?code=" + account.getVerificationCode();
        mailContent += "<h3><a href=\"" + verifyURL + "\" target=_blank >Click to verify your account</a></h3>";

        mailContent += "<p> Thank you <br>The Mascota en Casa Team </p>";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(emailFrom, senderName);
        helper.setTo(account.getEmail());
        helper.setSubject(subject);
        helper.setText(mailContent, true);

        javaMailSender.send(message);
    }
    @Override
    public Optional<Account> findByEmail(String email) {
        try {
            if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        return accountRepository.findByEmail(email);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error finding account by email: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error finding account by email", e);
        }
    }

    @Transactional
    @Override
    public void changeRole(Role udpdateRole, String account) {
        try {
            if (udpdateRole == null) {
                throw new IllegalArgumentException("Role cannot be null");
            }

            if (account == null || account.isEmpty()) {
                throw new IllegalArgumentException("Account cannot be null or empty");
            }
            accountRepository.updateUserRole(account, udpdateRole);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error changing role: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error changing role", e);
        }
    }
    @Transactional
    @Override
    public boolean verifyAccount(String verificationCode) {
        Account account = accountRepository.findByVerificationCode(verificationCode);

        if (account == null || account.isActive()) {
            return false;
        } else {
            account.setActive(true);
            return true;
        }
    }
    @Override
    public Account findByAccountReturnToken(String account) {
        Account email = accountRepository.findByEmail(account)
                .orElseThrow(() -> new UsernameNotFoundException("The account does not exist." + account));
        String jwt = jwtProvider.generateToken(email);
        email.setToken(jwt);
        return email;
    }

    @Transactional
    @Override
    public Account updateAccount(AccountDTO accountDTO) {
        try {
            Account existingAccount = accountRepository.findByEmail(accountDTO.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("The account does not exist." + accountDTO.getEmail()));

            AccountDetailsDTO accountDetailsDTO = accountDTO.getAccountDetails();
            AccountDetails accountDetails = existingAccount.getAccountDetails();

            if (accountDetails == null) {
                accountDetails = new AccountDetails();
                accountDetails.setAccountDetailsuuid(UUID.randomUUID().toString());
                accountDetails.setAccount(existingAccount);
            }

            // Validate if PhonesList is null or empty, and insert new phones if necessary
            List<Phones> phonesList = accountDetails.getPhonesList();
            if (phonesList == null || phonesList.isEmpty()) {
                phonesList = new ArrayList<>();
                accountDetails.setPhonesList(phonesList);

                for (PhonesDTO phoneDTO : accountDetailsDTO.getPhonesList()) {
                    Phones newPhone = new Phones();
                    newPhone.setPhoneUuid(UUID.randomUUID().toString());
                    newPhone.setPhoneLabel(phoneDTO.getPhoneLabel());
                    newPhone.setNumber(phoneDTO.getNumber());
                    newPhone.setCityCode(phoneDTO.getCityCode());
                    newPhone.setCountryCode(phoneDTO.getCountryCode());
                    phonesList.add(newPhone);
                }
            }

            // Update the phones list in AccountDetails
            accountDetails.setPhonesList(phonesList);

            BeanUtils.copyProperties(accountDetailsDTO, accountDetails, "accountDetailsuuid", "account", "phonesList");
            String identificationType = accountDetails.getIdentificationType().name();
            accountDetails.setIdentificationType(IdentificationType.valueOf(identificationType));

            // Update Phones
            List<Phones> updatedPhones = new ArrayList<>();
            Set<PhoneLabel> existingPhoneLabels = new HashSet<>();

            for (PhonesDTO phoneDTO : accountDetailsDTO.getPhonesList()) {
                PhoneLabel phoneLabel = phoneDTO.getPhoneLabel();
                String phoneNumber = phoneDTO.getNumber();

                Phones existingPhone = accountDetails.getPhonesList().stream()
                        .filter(phone -> phone.getPhoneLabel() == phoneLabel)
                        .findFirst()
                        .orElseGet(() -> {
                            Phones newPhone = new Phones();
                            newPhone.setPhoneUuid(UUID.randomUUID().toString());
                            newPhone.setPhoneLabel(phoneLabel);
                            existingPhoneLabels.add(phoneLabel);
                            return newPhone;
                        });

                existingPhone.setNumber(phoneNumber);
                existingPhone.setCityCode(phoneDTO.getCityCode());
                existingPhone.setCountryCode(phoneDTO.getCountryCode());
                updatedPhones.add(existingPhone);
            }

            // Remove phones with phoneLabels that no longer exist
            List<Phones> existingPhones = accountDetails.getPhonesList();
            existingPhones.removeIf(phone -> !existingPhoneLabels.contains(phone.getPhoneLabel()));

            accountDetails.setPhonesList(updatedPhones);

            existingAccount.setAccountDetails(accountDetails);

            return accountRepository.save(existingAccount);
        } catch (Exception e) {
            throw new RuntimeException("Error updating the account" + e.getMessage());
        }
    }
    @Override
    public AccountDTO getCurrentAccount(Account account){
        if (account != null && account.getAccountDetails() != null) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setUuid(account.getAccountUuid());
            accountDTO.setEmail(account.getEmail());
            accountDTO.setPassword(account.getPassword());
            accountDTO.setRol(account.getRol().toString());

            AccountDetails accountDetails = account.getAccountDetails();
            AccountDetailsDTO accountDetailsDTO = new AccountDetailsDTO();
            accountDetailsDTO.setFullName(accountDetails.getFullName());
            accountDetailsDTO.setIdentificationType(accountDetails.getIdentificationType());
            accountDetailsDTO.setIdentification(accountDetails.getIdentification());
            accountDetailsDTO.setCity(accountDetails.getCity());
            accountDetailsDTO.setAddress(accountDetails.getAddress());
            accountDetailsDTO.setSex(accountDetails.getSex());
            accountDetailsDTO.setDateBirth(accountDetails.getDateBirth());

            List<Phones> phonesList = accountDetails.getPhonesList();
            if (phonesList != null && !phonesList.isEmpty()) {
                List<PhonesDTO> phonesDTOList = phonesList.stream()
                        .map(phones -> {
                            PhonesDTO phonesDTO = new PhonesDTO();
                            phonesDTO.setPhoneUuid(phones.getPhoneUuid());
                            phonesDTO.setPhoneLabel(phones.getPhoneLabel());
                            phonesDTO.setNumber(phones.getNumber());
                            phonesDTO.setCityCode(phones.getCityCode());
                            phonesDTO.setCountryCode(phones.getCountryCode());
                            return phonesDTO;
                        })
                        .collect(Collectors.toList());
                accountDetailsDTO.setPhonesList(phonesDTOList);
            }

            accountDTO.setAccountDetails(accountDetailsDTO);
            accountDTO.setToken(account.getToken());

            return accountDTO;
        }
        if (account != null){
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setUuid(account.getAccountUuid());
            accountDTO.setEmail(account.getEmail());
            accountDTO.setPassword(account.getPassword());
            accountDTO.setRol(account.getRol().toString());
            accountDTO.setToken(account.getToken());

            return accountDTO;
        }
        else {
            throw new IllegalArgumentException("Account not found");
        }
    }
}
