package com.forohub.forohub.domain.curso;

public record DatosListarCurso(
        Long id,
        String nombre,
        String categoria
) {
    public DatosListarCurso(Curso curso){
        this(curso.getId(),curso.getNombre(),curso.getCategoria());
    }
}
