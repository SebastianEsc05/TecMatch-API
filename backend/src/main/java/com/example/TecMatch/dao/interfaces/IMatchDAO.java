package com.example.TecMatch.dao.interfaces;

import com.example.TecMatch.domain.Match;
import java.util.List;

public interface IMatchDAO {
    void crear(Match match);
    Match actualizar(Match match);
    void eliminar(Long id);
    Match buscarPorId(Long id);
    List<Match> buscarTodos();
    Match findMatchEntreUsuarios(Long usuario1Id, Long usuario2Id);
}