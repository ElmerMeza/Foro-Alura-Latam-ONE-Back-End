package com.forohub.forohub.domain.respuesta;

import com.forohub.forohub.domain.topico.TopicoRepository;
import com.forohub.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatosCrearRespuestaService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private RespuestaRepositorio repositorio;

    public DatosRespuestaRespuesta crearRespuesta(DatosCrearRespuesta datos){
        if (!usuarioRepository.findById(datos.autor()).isPresent()){
            throw new RuntimeException("El id de usuario ingresado no se encuentra");
        }
        if(!topicoRepository.findById(datos.topico()).isPresent()){
            throw new RuntimeException("El id del topico ingresado no se encuentra");
        }
        var autor=usuarioRepository.findById(datos.autor()).get();
        var topico=topicoRepository.findById(datos.topico()).get();

        var respuesta=new Respuesta(datos,autor,topico);
        repositorio.save(respuesta);

        return new DatosRespuestaRespuesta(respuesta);
    }
}
