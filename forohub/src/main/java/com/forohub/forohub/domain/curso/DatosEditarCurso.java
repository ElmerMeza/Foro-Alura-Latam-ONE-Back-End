package com.forohub.forohub.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosEditarCurso(
        @NotNull
        Long id,
        @NotBlank
        String nombre,
        String categoria
) {
}
