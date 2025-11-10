package potronet.mappers;

import potronet.entities.Chat;
import potronet.entities.ChatUsuario;
import potronet.entities.Usuario;
import potronet.dto.ChatUsuarioDTO;

public class ChatUsuarioMapper {

    public static ChatUsuarioDTO mapToDTO(ChatUsuario chatUsuario) {
        if (chatUsuario == null) {
            return null;
        }
        ChatUsuarioDTO chatUsuarioDTO = new ChatUsuarioDTO();
        chatUsuarioDTO.setId(chatUsuario.getId());
        if (chatUsuario.getChat() != null) {
            chatUsuarioDTO.setChatId(chatUsuario.getChat().getId());
        }
        if (chatUsuario.getUsuario() != null) {
            chatUsuarioDTO.setUsuarioId(chatUsuario.getUsuario().getId());
        }
        return chatUsuarioDTO;
    }

    public static ChatUsuario mapToEntity(ChatUsuarioDTO chatUsuarioDTO) {
        if (chatUsuarioDTO == null) {
            return null;
        }
        ChatUsuario chatUsuario = new ChatUsuario();
        chatUsuario.setId(chatUsuarioDTO.getId());

        if (chatUsuarioDTO.getChatId() != null) {
            Chat chat = new Chat();
            chat.setId(chatUsuarioDTO.getChatId());
            chatUsuario.setChat(chat);
        }
        if (chatUsuarioDTO.getUsuarioId() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(chatUsuarioDTO.getUsuarioId());
            chatUsuario.setUsuario(usuario);
        }
        return chatUsuario;
    }
}
