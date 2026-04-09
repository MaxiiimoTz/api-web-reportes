package pe.company.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.company.model.Reporte;
import pe.company.model.ReporteAsignado;
import pe.company.model.Usuario;
import pe.company.repository.ReporteAsignadoRepository;
import pe.company.repository.ReporteRepository;
import pe.company.repository.UsuarioRepository;
import pe.company.service.ReporteAsignadoService;

import java.util.List;

@Service
public class ReporteAsignadoServiceImpl implements ReporteAsignadoService {

    @Autowired
    private ReporteAsignadoRepository repository;

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public ReporteAsignado asignar(Integer reporteId, Integer usuarioId) {

        Reporte reporte = reporteRepository.findById(reporteId)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        ReporteAsignado asignado = new ReporteAsignado();
        asignado.setReporte(reporte);
        asignado.setUsuario(usuario);

        return repository.save(asignado);
    }

    @Override
    public List<ReporteAsignado> listarPorUsuario(Integer usuarioId) {
        return repository.findByUsuario_Id(usuarioId);
    }
}