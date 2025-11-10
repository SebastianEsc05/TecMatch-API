package potronet.repositories;

import potronet.entities.HobbieUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HobbieUsuarioRepository extends JpaRepository<HobbieUsuario,Long> {
}
