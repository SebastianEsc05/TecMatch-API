package com.example.TecMatch.repositories;

import com.example.TecMatch.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    boolean existsByLikerIdAndLikedId(Long likerId, Long likedId);
    void deleteByLikerIdAndLikedId(Long likerId, Long likedId);
}
