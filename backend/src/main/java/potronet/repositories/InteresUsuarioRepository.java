package potronet.repositories;

import potronet.entities.InteresUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteresUsuarioRepository extends JpaRepository<InteresUsuario,Long> {
}
