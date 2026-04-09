package pe.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.company.model.Adjunto;

import java.util.List;

public interface AdjuntoRepository extends JpaRepository<Adjunto, Integer> {

    List<Adjunto> findByReporte_Id(Integer reporteId);

}