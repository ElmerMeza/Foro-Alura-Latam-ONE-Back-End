package com.forohub.forohub.domain.respuesta;

public record DatosRespuestaRespuesta(
        Long id,
        String mensaje,
        String fechaCreacion,
        Long autor,
        Long topico
) {
    public DatosRespuestaRespuesta(Respuesta respuesta){
        this(respuesta.getId(),respuesta.getMensaje(),respuesta.getFechaCreacion().toString(),respuesta.getAutor().getId(),respuesta.getTopico().getId());
    }
}
