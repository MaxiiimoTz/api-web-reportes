package pe.company.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.company.model.Reporte;
import pe.company.repository.ReporteRepository;
import pe.company.service.ReporteService;

import java.util.List;

@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Override
    public List<Reporte> listar() {
        return reporteRepository.findAll();
    }

    @Override
    public Reporte crear(Reporte reporte) {

        long total = reporteRepository.count() + 1;
        String codigo = "REP" + String.format("%04d", total);

        reporte.setCodigo(codigo);

        return reporteRepository.save(reporte);
    }
    
    @Override
    public Reporte actualizar(int id, Reporte data) {

        Reporte r = reporteRepository.findById(id).orElse(null);

        if (r == null) return null;

        r.setNombre(data.getNombre());
        r.setEstado(data.getEstado());
        r.setTipo(data.getTipo());
        r.setFuente(data.getFuente());

        r.setMedio(data.getMedio());
        r.setTipoEnvio(data.getTipoEnvio());
        r.setFrecuencia(data.getFrecuencia());
        r.setDias(data.getDias());
        r.setHoraEnvio(data.getHoraEnvio());

        return reporteRepository.save(r);
    }
    
  
}