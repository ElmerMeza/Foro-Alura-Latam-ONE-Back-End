package com.forohub.forohub.domain.topico;

public record DatosListarTopico(Long id,String titulo,String mensaje,String fechaCreacion,String estado) {

    public DatosListarTopico(Topico topico){
        this(topico.getId(),topico.getTitulo(),topico.getMensaje(),topico.getFechaCreacion().toString(),topico.getEstado().toString());
    }
}
