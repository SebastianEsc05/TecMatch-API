package potronet.repositories;

import potronet.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    boolean existsByLikerIdAndLikedId(Long likerId, Long likedId);
    void deleteByLikerIdAndLikedId(Long likerId, Long likedId);

    @Query("SELECT COUNT(l) FROM Like l")
    Long contarLikes();
}
