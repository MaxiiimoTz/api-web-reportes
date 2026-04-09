package pe.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.company.model.Adjunto;
import pe.company.repository.AdjuntoRepository;
import pe.company.repository.ReporteRepository;

import java.util.List;

@RestController
@RequestMapping("/api/adjuntos")
@CrossOrigin("*")
public class AdjuntoController {

    @Autowired
    private AdjuntoRepository repository;

    @Autowired
    private ReporteRepository reporteRepository;

    @GetMapping("/{reporteId}")
    public List<Adjunto> listar(@PathVariable Integer reporteId) {
        return repository.findByReporte_Id(reporteId);
    }

    @PostMapping("/{reporteId}")
    public Adjunto guardar(@PathVariable Integer reporteId,
                           @RequestBody Adjunto adjunto) {

        adjunto.setReporte(reporteRepository.findById(reporteId).orElseThrow());
        return repository.save(adjunto);
    }
}