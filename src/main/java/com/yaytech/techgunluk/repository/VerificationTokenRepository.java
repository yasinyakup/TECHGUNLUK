package com.yaytech.techgunluk.repository;

import com.yaytech.techgunluk.model.VerificationToken;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{

    Optional<VerificationToken> findByToken(String token);
}
