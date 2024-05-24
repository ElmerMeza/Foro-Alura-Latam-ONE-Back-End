package com.forohub.forohub.controller;

import com.forohub.forohub.domain.respuesta.*;
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

import java.util.Optional;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private RespuestaRepositorio repositorio;
    @Autowired
    private DatosCrearRespuestaService service;

    @PostMapping
    @Transactional
    public ResponseEntity crearRespuesta(@RequestBody @Valid DatosCrearRespuesta datosCrearRespuesta, UriComponentsBuilder uriComponentsBuilder){
        var response=service.crearRespuesta(datosCrearRespuesta);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<DatosListarRespuesta>> listarRespuestas(@PageableDefault(size = 2) Pageable paginacion){
        return ResponseEntity.ok(repositorio.findAll(paginacion).map(DatosListarRespuesta::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListarRespuesta> listarRespuestId(@PathVariable Long id){
        Optional<Respuesta> respuesta=repositorio.findById(id);
        if (respuesta.isPresent()){
            Respuesta respuesta1=respuesta.get();
            var datos=new DatosListarRespuesta(respuesta1.getId(),respuesta1.getMensaje(),respuesta1.getFechaCreacion().toString());
            return ResponseEntity.ok(datos);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity editarrespuesta(@RequestBody @Valid DatosEditarRespuesta datos){
        Optional<Respuesta> respuesta=repositorio.findById(datos.id());
        if (respuesta.isPresent()){
            Respuesta respuesta1=respuesta.get();
            repositorio.getReferenceById(respuesta1.getId());
            respuesta.get().editar(datos);
            return ResponseEntity.ok(new DatosRespuestaRespuesta(respuesta1.getId(),respuesta1.getMensaje(),respuesta1.getFechaCreacion().toString(),respuesta1.getAutor().getId(),respuesta1.getTopico().getId()));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borrarRespuesta(@PathVariable Long id){
        Optional<Respuesta> respuesta=repositorio.findById(id);
        if (respuesta.isPresent()){
            repositorio.deleteById(id);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
