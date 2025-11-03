package com.example.PotroNet.dao.springRepositories;

import com.example.PotroNet.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Long> {
}
