package pe.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.company.model.Programacion;
import pe.company.repository.ProgramacionRepository;
import pe.company.repository.ReporteRepository;

@RestController
@RequestMapping("/api/programacion")
@CrossOrigin("*")
public class ProgramacionController {

    @Autowired
    private ProgramacionRepository repository;

    @Autowired
    private ReporteRepository reporteRepository;

    @PostMapping("/{reporteId}")
    public Programacion guardar(@PathVariable Integer reporteId,
                                @RequestBody Programacion p) {

        p.setReporte(reporteRepository.findById(reporteId).orElseThrow());
        return repository.save(p);
    }

    @GetMapping("/{reporteId}")
    public Programacion obtener(@PathVariable Integer reporteId) {
        return repository.findByReporte_Id(reporteId);
    }
}