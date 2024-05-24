package com.forohub.forohub.domain.curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Curso")
@Table(name = "cursos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String categoria;

    public Curso(DatosCrearCurso datosCrearCurso){
        this.nombre=datosCrearCurso.nombre();
        this.categoria=datosCrearCurso.categoria();
    }
    public void actualizarCurso(DatosEditarCurso datosEditarCurso){
        if (datosEditarCurso.nombre()!=null){
            this.nombre= datosEditarCurso.nombre();
        }
        if (datosEditarCurso.categoria()!=null){
            this.categoria= datosEditarCurso.categoria();
        }
    }

}
