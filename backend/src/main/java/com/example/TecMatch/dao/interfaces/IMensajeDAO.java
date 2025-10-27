package com.example.TecMatch.dao.interfaces;

import com.example.TecMatch.domain.Mensaje;
import java.util.List;

public interface IMensajeDAO {
    void crear(Mensaje mensaje);
    Mensaje buscarPorId(Long id);
    List<Mensaje> listar(int limite);
    Mensaje actualizar(Mensaje mensaje);
    void eliminar(Long id);
    List<Mensaje> listarPorChatId(Long chatId, int limite);

}
