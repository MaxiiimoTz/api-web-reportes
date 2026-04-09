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
}