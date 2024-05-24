package com.forohub.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosEditarTopico (
        @NotNull
        Long id,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje
){
}
