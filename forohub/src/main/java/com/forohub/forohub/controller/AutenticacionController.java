package com.forohub.forohub.controller;

import com.forohub.forohub.domain.usuario.DatosAutentificacionUsuario;
import com.forohub.forohub.domain.usuario.Usuario;
import com.forohub.forohub.infra.security.DatosJWT;
import com.forohub.forohub.infra.security.SecurityFilter;
import com.forohub.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SecurityFilter filters;

    @PostMapping
    public ResponseEntity autenticar(@RequestBody @Valid DatosAutentificacionUsuario datosAutentificacionUsuario){
        String clave= datosAutentificacionUsuario.clave();
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutentificacionUsuario.email(),
                datosAutentificacionUsuario.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);

        tokenService.setClave(clave);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok(new DatosJWT(JWTtoken));
    }
}
