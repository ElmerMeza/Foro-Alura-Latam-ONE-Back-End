package com.forohub.forohub.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record DatosCrearCurso(
        @NotBlank
        String nombre,
        String categoria
) {
}
