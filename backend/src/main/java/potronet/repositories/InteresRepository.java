package potronet.repositories;

import potronet.entities.Interes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface InteresRepository extends JpaRepository<Interes, Long> {
    Optional<Interes> findByDescripcionIgnoreCase(String descripcion);

    @Query("SELECT COUNT(i) FROM Interes i")
    Long contarIntereses();
}
