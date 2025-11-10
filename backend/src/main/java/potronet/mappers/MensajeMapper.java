package potronet.mappers;

import potronet.entities.Chat;
import potronet.entities.Mensaje;
import potronet.entities.Usuario;
import potronet.dto.MensajeDTO;

public class MensajeMapper {
    public static Mensaje mapToEntity(MensajeDTO mensajeDTO) {
        if (mensajeDTO == null) return null;
        Mensaje mensaje = new Mensaje();
        mensaje.setId(mensajeDTO.getId());
        mensaje.setTexto(mensajeDTO.getTexto());
        mensaje.setFecha_hora(mensajeDTO.getFechaHora());
        mensaje.setVisto(mensajeDTO.isVisto());

        if(mensajeDTO.getEmisorId() != null){
            Usuario emisor = new Usuario();
            emisor.setId(mensajeDTO.getEmisorId());
            mensaje.setEmisor(emisor);
        }
        if(mensajeDTO.getChatId() != null) {
            Chat chat = new Chat();
            chat.setId(mensajeDTO.getChatId());
            mensaje.setChat(chat);
        }
        return mensaje;
    }

    public static MensajeDTO mapToDTO(Mensaje mensaje) {
        if (mensaje == null) return null;

        MensajeDTO mensajeDTO = new MensajeDTO();
        mensajeDTO.setId(mensaje.getId());
        mensajeDTO.setTexto(mensaje.getTexto());
        mensajeDTO.setFechaHora(mensaje.getFecha_hora());
        mensajeDTO.setVisto(mensaje.isVisto());

        if (mensaje.getEmisor() != null) {
            mensajeDTO.setEmisorId(mensaje.getEmisor().getId());
        }

        if (mensaje.getChat() != null) {
            mensajeDTO.setChatId(mensaje.getChat().getId());
        }

        return mensajeDTO;

    }
}
