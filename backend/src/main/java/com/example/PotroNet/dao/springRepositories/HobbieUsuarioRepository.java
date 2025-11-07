package com.example.PotroNet.dao.springRepositories;

import com.example.PotroNet.domain.HobbieUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HobbieUsuarioRepository extends JpaRepository<HobbieUsuario,Long> {
}
