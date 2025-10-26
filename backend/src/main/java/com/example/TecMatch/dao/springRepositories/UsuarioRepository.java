package com.example.TecMatch.dao.springRepositories;
import com.example.TecMatch.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByCorreo(String correo);

    /**
     * Metodo para listar a los usuarios mas populares (con más Likes)
     * @param offset
     * @return
     */
    @Query(value = "CALL listarPorLikes(:Param1)", nativeQuery = true)
    Optional<Usuario> findByLikes(@Param("Param1") int offset);

    /**
     * Metodo para listar a los usuarios mas polemicos (con más dislikes)
     * @param offset
     * @return
     */
    @Query(value = "CALL listarPorDislikes(:Param1);",nativeQuery = true)
    Optional<Usuario> findByDislikes(@Param("Param1") int offset);

    /**
     * Metodo para listar de manera segregada a usuarios con el mismo hobbie
     * @param descripcion
     * @param offset
     * @return
     */
    @Query(value = "CALL listarAlumnosPorHobbies(:Param1,:Param2)",nativeQuery = true)
    Optional<Usuario> findByHobbies(@Param("Param1") String descripcion,
                                    @Param("Param2")int offset);

    /**
     * Metodo para listar de manera segregada a usuarios con el mismo Interes
     * @param descripcion
     * @param offset
     * @return
     */
    @Query(value= "CALL listarAlumnosPorIntereses(:Param1,:Param2)",nativeQuery = true)
    Optional<Usuario> findByInterests(@Param("Param1")String descripcion,
                                      @Param("Param2") int offset);

    /**
     * Metodo para listar a los usuarios que le dieron like a un usuario en especifico
     * @param id
     * @param offset
     * @return
     */
    @Query(value = "CALL listarLikes(:Param1,:Param2)",nativeQuery = true)
    Optional<Usuario> showLikes(@Param("Param1")int id,
                                @Param("Param2")int offset);

    /**
     * Metodo para listar a los usuarios que le dieron dislike a un usuario en especifico
     * @param id
     * @param offset
     * @return
     */
    @Query(value = "CALL listarDislikes(:Param1,:Param2)",nativeQuery = true)
    Optional<Usuario> showDisikes(@Param("Param1")int id,
                                @Param("Param2")int offset);

    /**
     * Metodo para listar a los usuarios que hicieron Match con un usuario
     * @param id
     * @param offset
     * @return
     */
    @Query(value = "CALL listarMatches(:Param1,:Param2)",nativeQuery = true)
    Optional<Usuario> showMatches(@Param("Param1")int id,
                                  @Param("Param2")int offset);
}
