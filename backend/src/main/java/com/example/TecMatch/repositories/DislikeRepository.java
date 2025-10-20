package com.example.TecMatch.repositories;

import com.example.TecMatch.models.Dislike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DislikeRepository extends JpaRepository<Dislike, Long> {
    boolean existsByDislikerIdAndDislikedId(Long likerId, Long likedId);
}
