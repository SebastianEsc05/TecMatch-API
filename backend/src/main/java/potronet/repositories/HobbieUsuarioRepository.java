package potronet.repositories;

import potronet.entities.HobbieUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import potronet.entities.Usuario;

import java.util.Optional;

@Repository
public interface HobbieUsuarioRepository extends JpaRepository<HobbieUsuario,Long> {
    void deleteByUsuario(Usuario usuario);
}
