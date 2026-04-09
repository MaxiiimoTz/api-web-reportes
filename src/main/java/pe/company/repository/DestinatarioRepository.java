package pe.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.company.model.Destinatario;

import java.util.List;

public interface DestinatarioRepository extends JpaRepository<Destinatario, Integer> {

    List<Destinatario> findByReporte_Id(Integer reporteId);

}