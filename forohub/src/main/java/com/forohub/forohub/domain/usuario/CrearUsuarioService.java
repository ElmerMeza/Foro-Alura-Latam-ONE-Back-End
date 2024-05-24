package com.forohub.forohub.domain.usuario;

import com.forohub.forohub.infra.security.SecurityConfiguration;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class CrearUsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private SecurityConfiguration security;

    public DatosRespuestaUsuario crearUsuario(DatosCrearUsuario datos, UriComponentsBuilder uri){
        if (repository.findByEmail(datos.email())!=null){
            throw new ValidationException("El email ya esta en uso");
        }

        String EClave=security.passwordEncoder().encode(datos.clave());
        Usuario usuario=new Usuario(datos);

        usuario.setClave(EClave);

        usuario=repository.save(usuario);
        DatosRespuestaUsuario datosRespuestaUsuario=new DatosRespuestaUsuario(usuario.getId(),usuario.getNombre(),usuario.getEmail());
        URI url=uri.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return datosRespuestaUsuario;
    }


}
