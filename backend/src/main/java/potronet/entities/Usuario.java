package potronet.entities;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String carrera;
    private String correo;
    private String descripcion;
    private String contrasenia;
    private String sexo;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String rutaFotoPerfil;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<InteresUsuario> interesUsuarios = new HashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<HobbieUsuario> hobbieUsuarios= new HashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Set<ChatUsuario> chatUsuarios = new HashSet<>();

    @OneToMany(mappedBy = "emisor", cascade = CascadeType.ALL)
    private Set<Mensaje> mensajes;

    public Usuario() {
    }

    public Usuario(Set<ChatUsuario> chatUsuarios, Set<HobbieUsuario> hobbieUsuarios, Set<InteresUsuario> interesUsuarios, String sexo, String contrasenia, String descripcion, String correo, String carrera, String nombre, String telefono, LocalDate fechaNacimiento, String rutaFotoPerfil) {
        this.chatUsuarios = chatUsuarios;
        this.hobbieUsuarios = hobbieUsuarios;
        this.interesUsuarios = interesUsuarios;
        this.sexo = sexo;
        this.contrasenia = contrasenia;
        this.descripcion = descripcion;
        this.correo = correo;
        this.carrera = carrera;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.rutaFotoPerfil = rutaFotoPerfil;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Set<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(Set<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Set<InteresUsuario> getInteresUsuarios() {
        return interesUsuarios;
    }

    public void setInteresUsuarios(Set<InteresUsuario> interesUsuarios) {
        this.interesUsuarios = interesUsuarios;
    }

    public Set<HobbieUsuario> getHobbieUsuarios() {
        return hobbieUsuarios;
    }

    public void setHobbieUsuarios(Set<HobbieUsuario> hobbieUsuarios) {
        this.hobbieUsuarios = hobbieUsuarios;
    }

    public Set<ChatUsuario> getChatUsuarios() {
        return chatUsuarios;
    }

    public void setChatUsuarios(Set<ChatUsuario> chatUsuarios) {
        this.chatUsuarios = chatUsuarios;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public String getRutaFotoPerfil() {
        return rutaFotoPerfil;
    }

    public void setRutaFotoPerfil(String rutaFotoPerfil) {
        this.rutaFotoPerfil = rutaFotoPerfil;
    }

    public List<String> getDescripcionHobbie() {
        return this.hobbieUsuarios.stream()
                .map(HobbieUsuario::getHobbie)
                .filter(Objects::nonNull)
                .map(Hobbie::getDescripcion)
                .toList();
    }

    public List<String> getDescripcionInteres() {
        return this.interesUsuarios.stream()
                .map(InteresUsuario::getInteres)
                .filter(Objects::nonNull)
                .map(Interes::getDescripcion)
                .toList();
    }




    @Override
    public String getPassword() {
        return this.contrasenia;
    }

    @Override
    public String getUsername() {
        return this.correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
