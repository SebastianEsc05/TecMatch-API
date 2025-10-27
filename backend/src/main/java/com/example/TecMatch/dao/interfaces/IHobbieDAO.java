package com.example.TecMatch.dao.interfaces;

import com.example.TecMatch.domain.Hobbie;
import java.util.List;

public interface IHobbieDAO {
    void crear(Hobbie hobbie);
    Hobbie buscarPorId(Long id);
    List<Hobbie> listar(int limite);
    Hobbie actualizar(Hobbie hobbie);
    void eliminar(Long id);
    Hobbie buscarPorDescripcion(String descripcion);
    Hobbie buscarOCrearPorDescripcion(String descripcion);
}