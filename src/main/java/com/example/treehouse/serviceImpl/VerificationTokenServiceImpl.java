package com.example.treehouse.serviceImpl;

import com.example.treehouse.dto.PersonResponse;
import com.example.treehouse.exception.CustomServiceExceptions;
import com.example.treehouse.model.Person;
import com.example.treehouse.model.VerificationToken;
import com.example.treehouse.repository.PersonRepository;
import com.example.treehouse.repository.VerificationTokenRepository;
import com.example.treehouse.service.VerificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class VerificationTokenServiceImpl implements VerificationService {

    private final VerificationTokenRepository verificationTokenRepository;
    public final PersonRepository personRepository;

    public VerificationTokenServiceImpl(VerificationTokenRepository verificationTokenRepository, PersonRepository personRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
        this.personRepository = personRepository;
    }

    public String saveVerificationToken(Person person){
        final String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(
                token, LocalDateTime.now(), LocalDateTime.now().plusHours(24), person);
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public Optional<VerificationToken> getToken(String token){
        return verificationTokenRepository.findByTokenCode(token);
    }

    public void setConfirmedAt(String token){
        verificationTokenRepository.findByTokenCode(token).ifPresent((confirm) ->
                confirm.setConfirmedAt(LocalDateTime.now()));
    }

    @Transactional
    public PersonResponse confirmToken(String token){
        VerificationToken verificationToken = getToken(token)
                .orElseThrow(() -> new CustomServiceExceptions("token not found"));

        if(verificationToken.getConfirmedAt() != null){
            throw new CustomServiceExceptions("email already confirmed");
        }

        LocalDateTime expiresAt = verificationToken.getExpiresAt();

        if(expiresAt.isBefore(LocalDateTime.now())){
            throw new CustomServiceExceptions("token expired");
        }

        setConfirmedAt(token);
        personRepository.findByEmail(verificationToken.getPerson().getEmail()).ifPresent((person) ->
                person.setVerifyEmail(true));
        return PersonResponse.builder().message("Email verified").build();
    }
}
