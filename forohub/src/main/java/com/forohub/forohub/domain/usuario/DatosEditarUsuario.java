package com.forohub.forohub.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record DatosEditarUsuario(
        @NotNull
        Long id,
        String nombre,
        String email,
        String clave
) {
}
