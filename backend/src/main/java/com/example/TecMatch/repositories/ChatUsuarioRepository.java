package com.example.TecMatch.repositories;

import com.example.TecMatch.models.Chat;
import com.example.TecMatch.models.ChatUsuario;
import com.example.TecMatch.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatUsuarioRepository extends JpaRepository<ChatUsuario,Long> {
    Optional<ChatUsuario> findByChatAndUsuarioNot(Chat chat, Usuario emisor);
}
