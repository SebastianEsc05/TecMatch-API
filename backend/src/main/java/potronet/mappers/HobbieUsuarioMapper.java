package potronet.mappers;

import potronet.entities.Hobbie;
import potronet.entities.HobbieUsuario;
import potronet.entities.Usuario;
import potronet.dto.HobbieUsuarioDTO;

public class HobbieUsuarioMapper {

    public static HobbieUsuarioDTO mapToDTO(HobbieUsuario hu) {
        if (hu == null) {
            return null;
        }

        HobbieUsuarioDTO dto = new HobbieUsuarioDTO();
        dto.setId(hu.getId());

        if (hu.getUsuario() != null) {
            dto.setUsuarioId(hu.getUsuario().getId());
            dto.setUsuarioNombre(hu.getUsuario().getNombre());
        }

        if (hu.getHobbie() != null) {
            dto.setHobbieId(hu.getHobbie().getId());
            dto.setHobbieDescripcion(hu.getHobbie().getDescripcion());
        }

        return dto;
    }

    public static HobbieUsuario mapToEntity(HobbieUsuarioDTO dto) {
        if (dto == null) {
            return null;
        }

        HobbieUsuario hu = new HobbieUsuario();
        hu.setId(dto.getId());

        if (dto.getUsuarioId() != null) {
            Usuario u = new Usuario();
            u.setId(dto.getUsuarioId());
            hu.setUsuario(u);
        }

        if (dto.getHobbieId() != null) {
            Hobbie h = new Hobbie();
            h.setId(dto.getHobbieId());
            hu.setHobbie(h);
        }

        return hu;
    }
}
