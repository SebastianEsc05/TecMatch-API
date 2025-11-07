package com.example.PotroNet.dao.springRepositories;

import com.example.PotroNet.domain.Interes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InteresRepository extends JpaRepository<Interes, Long> {
    Optional<Interes> findByDescripcionIgnoreCase(String descripcion);
}
