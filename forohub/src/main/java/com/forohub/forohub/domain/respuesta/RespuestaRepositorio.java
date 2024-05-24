package com.forohub.forohub.domain.respuesta;

import com.forohub.forohub.domain.topico.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaRepositorio extends JpaRepository<Respuesta,Long> {
    Page<Respuesta> findAll(Pageable pageable);
}
