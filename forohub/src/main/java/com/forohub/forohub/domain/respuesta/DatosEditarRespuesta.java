package com.forohub.forohub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosEditarRespuesta(
        @NotNull
        Long id,
        @NotBlank
        String mensaje
) {
}
