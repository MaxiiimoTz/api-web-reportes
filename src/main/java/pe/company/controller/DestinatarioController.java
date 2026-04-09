package pe.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.company.model.Destinatario;
import pe.company.repository.DestinatarioRepository;
import pe.company.repository.ReporteRepository;

import java.util.List;

@RestController
@RequestMapping("/api/destinatarios")
@CrossOrigin("*")
public class DestinatarioController {

    @Autowired
    private DestinatarioRepository repository;

    @Autowired
    private ReporteRepository reporteRepository;

    @GetMapping("/{reporteId}")
    public List<Destinatario> listar(@PathVariable Integer reporteId) {
        return repository.findByReporte_Id(reporteId);
    }

    @PostMapping("/{reporteId}")
    public Destinatario guardar(@PathVariable Integer reporteId,
                                @RequestBody Destinatario d) {

        d.setReporte(reporteRepository.findById(reporteId).orElseThrow());
        return repository.save(d);
    }
}