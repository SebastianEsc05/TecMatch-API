package com.example.TecMatch.dao.springRepositories;

import com.example.TecMatch.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match,Long> {
    @Query("SELECT m FROM Match m WHERE (m.usuario1.id = :id1 AND m.usuario2.id = :id2) OR (m.usuario1.id = :id2 AND m.usuario2.id = :id1)")
    Optional<Match> findMatchByUsuarios(@Param("id1") Long id1, @Param("id2") Long id2);

}
