package com.example.PotroNet.dao.springRepositories;

import com.example.PotroNet.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u " +
            "LEFT JOIN FETCH u.interesUsuarios iu " +
            "LEFT JOIN FETCH u.hobbieUsuarios hu " +
            "WHERE u.correo = :correo")
    Optional<Usuario> findFullProfileByCorreo(@Param("correo") String correo);

    Optional<Usuario> findByCorreo(String correo);

    /**
     * Filtrar usuarios por nombre de carrera (paginado)
     */
    Page<Usuario> findByCarreraContainingIgnoreCase(String carrera, Pageable pageable);

    /**
     * Filtrar usuarios por descripción de hobbie (paginado)
     */
    @Query("SELECT DISTINCT u FROM Usuario u " +
            "JOIN u.hobbieUsuarios hu " +
            "JOIN hu.hobbie h " +
            "WHERE LOWER(h.descripcion) LIKE LOWER(CONCAT('%', :descripcion, '%'))")
    Page<Usuario> findByHobbieDescripcion(@Param("descripcion") String descripcion, Pageable pageable);

    /**
     * Filtrar usuarios por descripción de interés (paginado)
     */
    @Query("SELECT DISTINCT u FROM Usuario u " +
            "JOIN u.interesUsuarios iu " +
            "JOIN iu.interes i " +
            "WHERE LOWER(i.descripcion) LIKE LOWER(CONCAT('%', :descripcion, '%'))")
    Page<Usuario> findByInteresDescripcion(@Param("descripcion") String descripcion, Pageable pageable);


    @Query(value = "SELECT * FROM usuarios u " +
            "WHERE u.carrera_nombre = :carreraNombre " +
            "AND u.id != :currentUserId " +
            "LIMIT 7 OFFSET :offset",
            nativeQuery = true)
    List<Usuario> findByCarreraNombreAndIdNot(
            @Param("carreraNombre") String carreraNombre,
            @Param("currentUserId") Long currentUserId,
            @Param("offset") int offset
    );

    @Query(value = "SELECT u.* FROM usuarios u " +
            "JOIN hobbies_usuarios hu ON u.id = hu.usuario_id " +
            "JOIN hobbies h ON hu.hobbie_id = h.id " +
            "WHERE h.descripcion IN (:descripciones) " +
            "GROUP BY u.id " +
            "LIMIT 7 OFFSET :offset",
            nativeQuery = true)
    List<Usuario> findByHobbiesIn(
            @Param("descripciones") List<String> descripciones,
            @Param("offset") int offset
    );

    @Query(value = "SELECT u.* FROM usuarios u " +
            "JOIN intereses_usuarios iu ON u.id = iu.usuario_id " +
            "JOIN intereses i ON iu.interes_id = i.id " +
            "WHERE i.descripcion IN (:descripciones) " +
            "GROUP BY u.id " +
            "LIMIT 7 OFFSET :offset",
            nativeQuery = true)
    List<Usuario> findByInterestsIn(
            @Param("descripciones") List<String> descripciones,
            @Param("offset") int offset
    );

    /**
     * Usuarios más populares (más likes)
     */
    @Query(value = "CALL listarPorLikes(:offset)", nativeQuery = true)
    Optional<Usuario> findByLikes(@Param("offset") int offset);

    /**
     * Usuarios más polémicos (más dislikes)
     */
    @Query(value = "CALL listarPorDislikes(:offset)", nativeQuery = true)
    Optional<Usuario> findByDislikes(@Param("offset") int offset);

    /**
     * Usuarios que dieron like a un usuario específico
     */
    @Query(value = "CALL listarLikes(:userId, :offset)", nativeQuery = true)
    Optional<Usuario> showLikes(@Param("userId") int userId, @Param("offset") int offset);

    /**
     * Usuarios que dieron dislike a un usuario específico
     */
    @Query(value = "CALL listarDislikes(:userId, :offset)", nativeQuery = true)
    Optional<Usuario> showDislikes(@Param("userId") int userId, @Param("offset") int offset);

    /**
     * Usuarios que hicieron Match con un usuario
     */
    @Query(value = "CALL listarMatches(:userId, :offset)", nativeQuery = true)
    Optional<Usuario> showMatches(@Param("userId") int userId, @Param("offset") int offset);

    /**
     * Usuarios según hobbie e interés
     */
    @Query(value = "CALL ListarPorHobbiesEIntereses(:hobbie, :interes, :offset)", nativeQuery = true)
    Optional<Usuario> findByHobbieAndInterest(
            @Param("hobbie") String descripcionHobbie,
            @Param("interes") String descripcionInteres,
            @Param("offset") int offset
    );
}
