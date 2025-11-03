package com.example.PotroNet.dao.interfaces;

import com.example.PotroNet.domain.Interes;
import java.util.List;

public interface IInteresDAO {
    void crear(Interes interes);
    Interes buscarPorId(Long id);
    List<Interes> listar(int limite);
    Interes actualizar(Interes interes);
    void eliminar(Long id);
    Interes buscarPorDescripcion(String descripcion);
    Interes buscarOCrearPorDescripcion(String descripcion);
}