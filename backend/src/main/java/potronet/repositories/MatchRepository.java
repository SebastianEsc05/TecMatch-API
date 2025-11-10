package potronet.repositories;

import potronet.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match,Long> {
    @Query("SELECT m FROM Match m WHERE (m.usuario1.id = :id1 AND m.usuario2.id = :id2) OR (m.usuario1.id = :id2 AND m.usuario2.id = :id1)")
    Optional<Match> findMatchByUsuarios(@Param("id1") Long id1, @Param("id2") Long id2);

    @Query("SELECT m FROM Match m WHERE m.usuario1.id = :userId OR m.usuario2.id = :userId")
    List<Match> findAllMatchesByUserId(@Param("userId") Long userId);
    @Query("SELECT COUNT(m) FROM Match m")
    Long contarMatches();
}
