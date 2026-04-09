package pe.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.company.model.Programacion;

public interface ProgramacionRepository extends JpaRepository<Programacion, Integer> {

    Programacion findByReporte_Id(Integer reporteId);

}