package pe.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.company.dto.CumplimientoDTO;
import pe.company.service.CumplimientoService;

import java.util.List;

@RestController
@RequestMapping("/api/cumplimiento")
@CrossOrigin("*")
public class CumplimientoController {

    @Autowired
    private CumplimientoService service;

    @GetMapping
    public List<CumplimientoDTO> dashboard() {
        return service.obtenerDashboard();
    }
}