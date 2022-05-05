package com.example.treehouse.service;

import com.example.treehouse.dto.PersonResponse;
import com.example.treehouse.model.Person;
import com.example.treehouse.model.VerificationToken;

import java.util.Optional;

public interface VerificationService {

    String saveVerificationToken(Person person);

    Optional<VerificationToken> getToken(String token);

    void setConfirmedAt(String token);

    PersonResponse confirmToken(String token);
}
