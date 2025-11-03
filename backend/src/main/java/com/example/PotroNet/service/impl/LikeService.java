package com.example.PotroNet.service.impl;
import com.example.PotroNet.dao.impl.LikeDAO;
import com.example.PotroNet.dao.impl.UsuarioDAO;
import com.example.PotroNet.dao.interfaces.ILikeDAO;
import com.example.PotroNet.dao.interfaces.IUsuarioDAO;
import com.example.PotroNet.domain.Like;
import com.example.PotroNet.domain.Usuario;
import com.example.PotroNet.dto.LikeDTO;
import com.example.PotroNet.dto.MatchDTO;
import com.example.PotroNet.mapper.LikeMapper;
import com.example.PotroNet.service.interfaces.ILikeService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class LikeService implements ILikeService {
    private final EntityManagerFactory emf;

    public LikeService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public MatchDTO crearLikeYVerificarMatch(LikeDTO likeDTO) throws Exception {
        EntityManager em = emf.createEntityManager();
        MatchDTO matchCreado = null;

        try {
            em.getTransaction().begin();

            ILikeDAO likeDAO = new LikeDAO(em);
            IUsuarioDAO usuarioDAO = new UsuarioDAO(em);

            Long likerId = likeDTO.getLikerId();
            Long likedId = likeDTO.getLikedId();

            if (likerId == null || likedId == null) {
                throw new Exception("Se requiere un 'likerId' y un 'likedId'.");
            }
            if (likerId.equals(likedId)) {
                throw new Exception("Un usuario no puede darse like a sí mismo.");
            }

            Usuario liker = usuarioDAO.buscarPorId(likerId);
            Usuario liked = usuarioDAO.buscarPorId(likedId);

            if (liker == null || liked == null) {
                throw new Exception("Uno o ambos usuarios no existen.");
            }

            Like likeExistente = likeDAO.findLike(likerId, likedId);
            if (likeExistente != null) {
                throw new Exception("Este like ya fue registrado.");
            }

            Like nuevoLike = new Like();
            nuevoLike.setLiker(liker);
            nuevoLike.setLiked(liked);
            nuevoLike.setFecha_hora(LocalDateTime.now());

            likeDAO.crear(nuevoLike);

            Like likeMutuo = likeDAO.findLike(likedId, likerId);

            if (likeMutuo != null) {
                MatchService matchService = new MatchService(emf);
                MatchDTO matchDTOParaCrear = new MatchDTO(null, likerId, likedId);
                try {
                    matchCreado = matchService.crearMatch(matchDTOParaCrear);
                } catch (Exception e) {
                    System.err.println("Advertencia al crear match: " + e.getMessage());
                }
            }
            em.getTransaction().commit();
            return matchCreado;

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error al dar like: " + e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void eliminarLike(Long id) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            ILikeDAO likeDAO = new LikeDAO(em);

            em.getTransaction().begin();
            Like like = likeDAO.buscarPorId(id);
            if (like == null) {
                throw new Exception("No se encontró el like con ID: " + id);
            }

            likeDAO.eliminar(id);
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error al eliminar el like: " + e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<LikeDTO> getLikesRecibidos(Long usuarioId) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            ILikeDAO likeDAO = new LikeDAO(em);

            List<Like> likes = likeDAO.findLikesRecibidos(usuarioId);

            return likes.stream()
                    .map(LikeMapper::mapToDTO)
                    .collect(Collectors.toList());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<LikeDTO> getLikesDados(Long usuarioId) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            ILikeDAO likeDAO = new LikeDAO(em);

            List<Like> likes = likeDAO.findLikesDados(usuarioId);

            return likes.stream()
                    .map(LikeMapper::mapToDTO)
                    .collect(Collectors.toList());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
