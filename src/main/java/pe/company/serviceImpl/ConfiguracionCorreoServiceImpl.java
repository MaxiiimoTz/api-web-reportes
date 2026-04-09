package pe.company.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.company.model.ConfiguracionCorreo;
import pe.company.model.Reporte;
import pe.company.repository.ConfiguracionCorreoRepository;
import pe.company.repository.ReporteRepository;
import pe.company.service.ConfiguracionCorreoService;

@Service
public class ConfiguracionCorreoServiceImpl implements ConfiguracionCorreoService {

    @Autowired
    private ConfiguracionCorreoRepository repository;

    @Autowired
    private ReporteRepository reporteRepository;

    @Override
    public ConfiguracionCorreo guardar(Integer reporteId, ConfiguracionCorreo config) {

        Reporte reporte = reporteRepository.findById(reporteId)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));

        ConfiguracionCorreo existente = repository.findByReporte_Id(reporteId);

        if (existente != null) {
            existente.setAsunto(config.getAsunto());
            existente.setMensaje(config.getMensaje());
            return repository.save(existente);
        }

        config.setReporte(reporte);
        return repository.save(config);
    }

    @Override
    public ConfiguracionCorreo obtener(Integer reporteId) {
        return repository.findByReporte_Id(reporteId);
    }
}