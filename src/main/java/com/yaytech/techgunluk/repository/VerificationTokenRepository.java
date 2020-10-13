package com.yaytech.techgunluk.repository;

import com.yaytech.techgunluk.model.VerificationToken;

import java.util.Optional;

public interface VerificationTokenRepository {

    Optional<VerificationToken> findByToken(String token);
}
