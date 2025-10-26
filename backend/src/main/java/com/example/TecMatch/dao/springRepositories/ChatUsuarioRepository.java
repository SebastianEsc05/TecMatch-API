package com.example.TecMatch.dao.springRepositories;

import com.example.TecMatch.domain.Chat;
import com.example.TecMatch.domain.ChatUsuario;
import com.example.TecMatch.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatUsuarioRepository extends JpaRepository<ChatUsuario,Long> {
    Optional<ChatUsuario> findByChatAndUsuarioNot(Chat chat, Usuario emisor);
}
