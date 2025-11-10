package potronet.services;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

@Service
public class StorageService {

    private final Cloudinary cloudinary;

    public StorageService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String guardarArchivoEnCloudinary(MultipartFile archivo) {
        try {
            if (archivo.isEmpty()) {
                System.out.println("Archivo vacío");
                throw new RuntimeException("El archivo está vacío");
            }

            if (!archivo.getContentType().startsWith("image/")) {
                System.out.println("No es una imagen: " + archivo.getContentType());
                throw new RuntimeException("Solo se permiten imágenes");
            }
            Map<String, Object> options = ObjectUtils.asMap(
                    "folder", "potronet/profile-pictures",
                    "resource_type", "auto",
                    "public_id", "user_" + System.currentTimeMillis(),
                    "overwrite", true
            );
            Map<?, ?> uploadResult = cloudinary.uploader().upload(archivo.getBytes(), options);
            String secureUrl = uploadResult.get("secure_url").toString();
            String publicId = uploadResult.get("public_id").toString();
            return secureUrl;
        } catch (IOException e) {
            throw new RuntimeException("Error al subir archivo a Cloudinary: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado: " + e.getMessage(), e);
        }
    }

    public void eliminarArchivoDeCloudinary(String publicId) {
        try {
            Map<?, ?> result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (Exception e) {
            return;
        }
    }
}