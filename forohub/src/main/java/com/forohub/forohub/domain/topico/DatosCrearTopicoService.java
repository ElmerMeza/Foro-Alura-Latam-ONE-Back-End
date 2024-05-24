package com.forohub.forohub.domain.topico;

import com.forohub.forohub.domain.curso.CursoRespository;
import com.forohub.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatosCrearTopicoService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRespository cursoRespository;
    @Autowired
    private TopicoRepository topicoRepository;

    public DatosRespuestaTopico crearTopico(DatosCrearTopico datos){
        if (!usuarioRepository.findById(datos.autorId()).isPresent()){
            throw new ValidationException("El id del autor no fue encontrado");
        }
        if (!cursoRespository.findById(datos.cursoId()).isPresent()){
            throw new ValidationException("El id del curso no fue encontrado");
        }

        var autor=usuarioRepository.findById(datos.autorId()).get();
        var curso=cursoRespository.findById(datos.cursoId()).get();

        var topico=new Topico(datos,autor,curso);

        topicoRepository.save(topico);
        return new DatosRespuestaTopico(topico);
    }

}
