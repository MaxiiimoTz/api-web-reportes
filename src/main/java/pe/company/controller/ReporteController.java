package pe.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.company.model.Reporte;
import pe.company.service.ReporteService;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin("*")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public List<Reporte> listar() {
        return reporteService.listar();
    }

    @PostMapping
    public Reporte crear(@RequestBody Reporte reporte) {
        return reporteService.crear(reporte);
    }
}