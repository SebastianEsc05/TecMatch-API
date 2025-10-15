package com.example.TecMatch.repositories;
import com.example.TecMatch.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByCorreo(String correo);
}
