package com.example.TecMatch.service.interfaces;

import com.example.TecMatch.dto.MatchDTO;
import java.util.List;

public interface IMatchService {
    MatchDTO crearMatch(MatchDTO matchDTO) throws Exception;
    void eliminarMatch(Long id) throws Exception;
    MatchDTO buscarMatchPorId(Long id) throws Exception;
    List<MatchDTO> buscarTodosLosMatches();
    MatchDTO getMatchEntreUsuarios(Long usuario1Id, Long usuario2Id) throws Exception;
}