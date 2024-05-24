package com.forohub.forohub.domain.topico;

import com.forohub.forohub.domain.curso.Curso;
import com.forohub.forohub.domain.curso.DatosCrearCurso;
import com.forohub.forohub.domain.estado.Estado;
import com.forohub.forohub.domain.respuesta.Respuesta;
import com.forohub.forohub.domain.usuario.DatosCrearUsuario;
import com.forohub.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private Estado estado= Estado.NO_RESPONDIDO;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario autor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "topico_id", referencedColumnName = "id")
    private List<Respuesta> respuesta= new ArrayList<>();


    public Topico(DatosCrearTopico datosCrearTopico, Usuario autor, Curso curso){
        this.titulo= datosCrearTopico.titulo();
        this.mensaje= datosCrearTopico.mensaje();
        this.fechaCreacion=LocalDateTime.now();
        this.autor=autor;
        this.curso=curso;

    }

    public void editarTopico(DatosEditarTopico datosEditarTopico){
        if (datosEditarTopico.titulo()!=null){
            this.titulo= datosEditarTopico.titulo();
        }
        if (datosEditarTopico.mensaje()!=null){
            this.mensaje= datosEditarTopico.mensaje();
        }
    }
}
