package potronet.repositories;

import potronet.entities.Hobbie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface HobbieRepository extends JpaRepository<Hobbie,Long> {
    Optional<Hobbie> findByDescripcionIgnoreCase(String descripcion);

    @Query("SELECT COUNT(h) FROM Hobbie h")
    Long contarHobbies();
}
