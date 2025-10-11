package com.example.TecMatch.repositories;

import com.example.TecMatch.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Long> {
}
