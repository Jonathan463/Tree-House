package com.example.treehouse.service;

import com.example.treehouse.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.io.IOException;

public interface PersonService {


    ResponseEntity<AuthResponse> loginUser(AuthRequest req) throws Exception;

    PersonInfoResponse getInfo(Authentication authentication) throws Exception;

    UpdatePersonResponse updateUserDetails(UpdatePersonRequest updatePersonRequest);

    PersonResponse register(PersonRequest personRequest) throws IOException;


    PersonResponse sendingEmail(String email) ;

    ChangePasswordResponse updateCurrentPassword(ChangePasswordRequest changePasswordRequest);

    PersonResponse resetPasswordToken(String email) ;

    PersonResponse updateResetPassword(ResetPasswordRequest passwordRequest, String token);

    void resetPasswordMailSender(String email, String token) ;

    String buildEmail(String name, String link);
}
