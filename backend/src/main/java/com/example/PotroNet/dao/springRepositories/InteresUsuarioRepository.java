package com.example.PotroNet.dao.springRepositories;

import com.example.PotroNet.domain.InteresUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteresUsuarioRepository extends JpaRepository<InteresUsuario,Long> {
}
