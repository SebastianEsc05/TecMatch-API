package com.example.TecMatch.service.interfaces;

import com.example.TecMatch.domain.Mensaje;

import java.util.List;

public interface IMensajeService {
    boolean crear(Mensaje mensaje);
    Mensaje buscarPorId(Long id);
    List<Mensaje> listar(int limite);
    boolean actualizar(Mensaje mensaje);
    boolean eliminar(Long id);
}
