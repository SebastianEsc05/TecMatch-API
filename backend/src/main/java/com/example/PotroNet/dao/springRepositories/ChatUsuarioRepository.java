package com.example.PotroNet.dao.springRepositories;

import com.example.PotroNet.domain.Chat;
import com.example.PotroNet.domain.ChatUsuario;
import com.example.PotroNet.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatUsuarioRepository extends JpaRepository<ChatUsuario,Long> {
}
