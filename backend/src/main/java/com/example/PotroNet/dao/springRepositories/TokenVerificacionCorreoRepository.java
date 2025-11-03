package com.example.PotroNet.dao.springRepositories;

import com.example.PotroNet.domain.TokenVerificacionCorreo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenVerificacionCorreoRepository extends JpaRepository<TokenVerificacionCorreo, Long> {
    Optional<TokenVerificacionCorreo> findByToken(String token);
}
