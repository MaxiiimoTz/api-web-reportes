package pe.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.company.model.Envio;

import java.time.LocalDateTime;
import java.util.List;

public interface EnvioRepository extends JpaRepository<Envio, Integer> {

    List<Envio> findByUsuario_Id(Integer usuarioId);

    List<Envio> findByReporte_Id(Integer reporteId);

    List<Envio> findByReporte_IdAndFechaEnvioBetween(
            Integer reporteId,
            LocalDateTime inicio,
            LocalDateTime fin
    );
}