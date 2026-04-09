package pe.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.company.model.ReporteAsignado;

import java.util.List;

public interface ReporteAsignadoRepository extends JpaRepository<ReporteAsignado, Integer> {

    List<ReporteAsignado> findByUsuario_Id(Integer usuarioId);

    List<ReporteAsignado> findByReporte_Id(Integer reporteId);
}