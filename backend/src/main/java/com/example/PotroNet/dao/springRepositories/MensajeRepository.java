package com.example.PotroNet.dao.springRepositories;

import com.example.PotroNet.domain.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje,Long> {
}
