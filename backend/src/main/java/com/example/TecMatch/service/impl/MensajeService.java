package com.example.TecMatch.service.impl;

import com.example.TecMatch.dao.impl.MensajeDAO;
import com.example.TecMatch.dao.interfaces.IMensajeDAO;
import com.example.TecMatch.domain.Mensaje;
import com.example.TecMatch.service.interfaces.IMensajeService;

import java.util.List;

public class MensajeService implements IMensajeService {
    private final IMensajeDAO mensajeDAO = new MensajeDAO();
    @Override
    public boolean crear(Mensaje mensaje) {
        return false;
    }

    @Override
    public Mensaje buscarPorId(Long id) {
        return null;
    }

    @Override
    public List<Mensaje> listar(int limite) {
        return List.of();
    }

    @Override
    public boolean actualizar(Mensaje mensaje) {
        return false;
    }

    @Override
    public boolean eliminar(Long id) {
        return false;
    }
}
