package pe.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.company.model.ReporteAsignado;
import pe.company.service.ReporteAsignadoService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/asignaciones")
@CrossOrigin("*")
public class ReporteAsignadoController {

    @Autowired
    private ReporteAsignadoService service;

    @PostMapping
    public ReporteAsignado asignar(@RequestBody Map<String, Integer> body) {

        return service.asignar(
                body.get("reporteId"),
                body.get("usuarioId")
        );
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<ReporteAsignado> listar(@PathVariable Integer usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }
}