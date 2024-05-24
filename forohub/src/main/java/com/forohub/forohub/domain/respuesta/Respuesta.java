package com.forohub.forohub.domain.respuesta;

import com.forohub.forohub.domain.topico.Topico;
import com.forohub.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Respuesta")
@Table(name = "respuestas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;
    private LocalDateTime fechaCreacion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario autor;
    private Boolean solucion=false;

    public Respuesta(DatosCrearRespuesta datos,Usuario autor,Topico topico){
        this.mensaje= datos.mensaje();
        this.topico=topico;
        this.fechaCreacion=LocalDateTime.now();
        this.autor=autor;
    }

    public void editar(DatosEditarRespuesta datosEditarRespuesta){
        if(datosEditarRespuesta.mensaje()!=null){
            this.mensaje= datosEditarRespuesta.mensaje();
            this.solucion=true;
        }
    }
}
