package com.project.colabeduc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.colabeduc.backend.entities.PasswordResetToken;

import java.util.Date;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

    void deleteByExpiryDateBefore(Date now);

    boolean existsByToken(String token);
}

