package pe.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.company.model.ConfiguracionCorreo;

public interface ConfiguracionCorreoRepository extends JpaRepository<ConfiguracionCorreo, Integer> {

    ConfiguracionCorreo findByReporte_Id(Integer reporteId);

}