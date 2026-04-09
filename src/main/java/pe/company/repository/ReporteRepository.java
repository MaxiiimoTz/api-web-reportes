package pe.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.company.model.Reporte;

public interface ReporteRepository extends JpaRepository<Reporte, Integer> {
}