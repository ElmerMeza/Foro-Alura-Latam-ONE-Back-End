package com.forohub.forohub.domain.respuesta;

public record DatosListarRespuesta(
        Long id,
        String mensaje,
        String fechaCreacion
) {
    public DatosListarRespuesta(Respuesta respuesta){
        this(respuesta.getId(),respuesta.getMensaje(),respuesta.getFechaCreacion().toString());
    }
}
