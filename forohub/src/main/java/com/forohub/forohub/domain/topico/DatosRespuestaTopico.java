package com.forohub.forohub.domain.topico;

public record DatosRespuestaTopico(Long id, String titulo, String mensaje, String fechaCreacion, String estado,
                                   Long autor, Long curso) {

    public DatosRespuestaTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion().toString(),
                topico.getEstado().toString(), topico.getAutor().getId(), topico.getCurso().getId());
    }
}
