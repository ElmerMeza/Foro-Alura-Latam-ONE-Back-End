package com.forohub.forohub.controller;

import com.forohub.forohub.domain.usuario.*;
import com.forohub.forohub.infra.security.SecurityConfiguration;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private CrearUsuarioService service;
    @Autowired
    private SecurityConfiguration security;

    @PostMapping
    @Transactional
    public ResponseEntity registroUsario(@RequestBody @Valid DatosCrearUsuario datosCrearUsuario, UriComponentsBuilder uriComponentsBuilder){
       var response=service.crearUsuario(datosCrearUsuario,uriComponentsBuilder);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListarUsuario>> listarUsuarios(@PageableDefault(size = 10) Pageable paginacion){
        return ResponseEntity.ok(repository.findAll(paginacion).map(DatosListarUsuario::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListarUsuario> listarUsuariosId(@PathVariable Long id){
            Optional<Usuario> usuario=repository.findById(id);
            if (usuario.isPresent()){
                Usuario u=usuario.get();
                var datosUsuario=new DatosListarUsuario(u.getId(),u.getNombre(),u.getEmail());
                return ResponseEntity.ok(datosUsuario);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    }

    @PutMapping()
    @Transactional
    public ResponseEntity editarUsuario(@RequestBody @Valid DatosEditarUsuario datosEditarUsuario){
        Optional<Usuario> usuario=repository.findById(datosEditarUsuario.id());
        if (usuario.isPresent()){
            String encode=security.passwordEncoder().encode(datosEditarUsuario.clave());
            Usuario u=usuario.get();
            usuario.get().ActualizarUsuario(datosEditarUsuario,encode);
            return ResponseEntity.ok(new DatosRespuestaUsuario(u.getId(),u.getNombre()
                    ,u.getEmail()));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrarUsuario(@PathVariable Long id){
        Optional<Usuario> usuario=repository.findById(id);
        if (usuario.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
