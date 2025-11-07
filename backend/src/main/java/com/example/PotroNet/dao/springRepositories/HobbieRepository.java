package com.example.PotroNet.dao.springRepositories;

import com.example.PotroNet.domain.Hobbie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HobbieRepository extends JpaRepository<Hobbie,Long> {
    Optional<Hobbie> findByDescripcionIgnoreCase(String descripcion);
}
