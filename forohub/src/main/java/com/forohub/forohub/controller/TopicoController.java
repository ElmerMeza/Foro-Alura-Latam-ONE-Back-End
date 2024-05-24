package com.forohub.forohub.controller;

import com.forohub.forohub.domain.topico.*;
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
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private DatosCrearTopicoService datosCrearTopicoService;

    @PostMapping
    @Transactional
    public ResponseEntity creartopicos(@RequestBody @Valid DatosCrearTopico datosCrearTopico, UriComponentsBuilder uriComponentsBuilder){
        var response=datosCrearTopicoService.crearTopico(datosCrearTopico);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListarTopico>> listarTopicos(@PageableDefault(size = 2) Pageable paginacion){
        return ResponseEntity.ok(repository.findAll(paginacion).map(DatosListarTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListarTopico> listarTopicoId(@PathVariable Long id){
        Optional<Topico> topico=repository.findById(id);
        if (topico.isPresent()){
            Topico topico1=topico.get();
            var datosTopico=new DatosListarTopico(topico1.getId(),topico1.getTitulo(),topico1.getMensaje(),
                    topico1.getFechaCreacion().toString(),topico1.getEstado().toString());
            return ResponseEntity.ok(datosTopico);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity editarTopico(@RequestBody @Valid DatosEditarTopico datosEditarTopico){
        Optional<Topico> topico=repository.findById(datosEditarTopico.id());
        if (topico.isPresent()){
            Topico topico1=topico.get();
            repository.getReferenceById(topico1.getId());
            topico.get().editarTopico(datosEditarTopico);
            return ResponseEntity.ok(new DatosRespuestaTopico(topico1.getId(),topico1.getTitulo(),topico1.getMensaje(),topico1.getFechaCreacion().toString(),
                    topico1.getEstado().toString(),topico1.getAutor().getId(),topico1.getCurso().getId()));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Optional<Topico> topico=repository.findById(id);
        if (topico.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
