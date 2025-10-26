package com.example.TecMatch.dao.interfaces;

import com.example.TecMatch.domain.Match;

import java.util.List;

public interface IMatchDAO {
    boolean crear(Match match);
    Match buscarPorId(Long id);
    List<Match> listar(int limite);
    boolean actualizar(Match match);
    boolean eliminar(Long id);


}
