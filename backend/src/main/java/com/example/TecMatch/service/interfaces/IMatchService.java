package com.example.TecMatch.service.interfaces;

import com.example.TecMatch.domain.Match;

import java.util.List;

public interface IMatchService {
    boolean crear(Match match);
    Match buscarPorId(Long id);
    List<Match> listar(int limite);
    boolean actualizar(Match match);
    boolean eliminar(Long id);

}
