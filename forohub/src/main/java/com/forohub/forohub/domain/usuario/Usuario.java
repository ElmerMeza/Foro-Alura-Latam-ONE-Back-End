package com.forohub.forohub.domain.usuario;

import com.forohub.forohub.domain.perfil.Perfil;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String clave;
    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    public Usuario(DatosCrearUsuario datosCrearUsuario) {
        this.nombre=datosCrearUsuario.nombre();
        this.email=datosCrearUsuario.email();
        this.clave=datosCrearUsuario.clave();
        this.perfil=Perfil.ROLE_USER;

    }

    public void ActualizarUsuario(DatosEditarUsuario datosEditarUsuario,String clave){
        if (datosEditarUsuario.nombre()!=null){
            this.nombre=datosEditarUsuario.nombre();
        }
        if (datosEditarUsuario.email()!=null){
            this.email=datosEditarUsuario.email();
        }
        if (datosEditarUsuario.clave()!=null){
            this.clave=clave;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return email;
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
