package com.forohub.forohub.controller;

import com.forohub.forohub.domain.curso.*;
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
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoRespository respository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaCurso> crearCurso(@RequestBody @Valid DatosCrearCurso datosCrearCurso, UriComponentsBuilder uriComponentsBuilder){
        Curso curso=respository.save(new Curso(datosCrearCurso));
        DatosRespuestaCurso datosRespuestaCurso=new DatosRespuestaCurso(curso.getId(),curso.getNombre(),curso.getCategoria());
        URI url=uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.ok(datosRespuestaCurso);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListarCurso>> listarCurso(@PageableDefault(size = 2) Pageable paginacion){
        return ResponseEntity.ok(respository.findAll(paginacion).map(DatosListarCurso::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListarCurso> ListarCursoId(@PathVariable Long id){
        Optional<Curso> curso=respository.findById(id);
        if (curso.isPresent()){
            Curso curso1=curso.get();
            var datosListar= new DatosListarCurso(curso1.getId(),curso1.getNombre(),curso1.getCategoria());
            return ResponseEntity.ok(datosListar);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity editarCurso(@RequestBody @Valid DatosEditarCurso datosEditarCurso){
        Optional<Curso> curso=respository.findById(datosEditarCurso.id());
        if (curso.isPresent()){
            Curso curso1=curso.get();
            respository.getReferenceById(curso1.getId());
            curso.get().actualizarCurso(datosEditarCurso);
            return ResponseEntity.ok(new DatosRespuestaCurso(curso1.getId(),curso1.getNombre(),curso1.getCategoria()));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrarCurso(@PathVariable Long id){
        Optional<Curso> curso=respository.findById(id);
        if (curso.isPresent()){
            respository.deleteById(id);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
