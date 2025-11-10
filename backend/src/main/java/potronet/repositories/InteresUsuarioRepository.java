package potronet.repositories;

import potronet.entities.InteresUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import potronet.entities.Usuario;

@Repository
public interface InteresUsuarioRepository extends JpaRepository<InteresUsuario,Long> {
    void deleteByUsuario(Usuario usuario);
}

