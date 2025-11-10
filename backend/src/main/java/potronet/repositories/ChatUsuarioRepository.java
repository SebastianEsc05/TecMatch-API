package potronet.repositories;

import potronet.entities.ChatUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatUsuarioRepository extends JpaRepository<ChatUsuario,Long> {
}
