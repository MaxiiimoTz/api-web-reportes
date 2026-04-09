package pe.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.company.model.ConfiguracionCorreo;
import pe.company.service.ConfiguracionCorreoService;

@RestController
@RequestMapping("/api/configuracion")
@CrossOrigin("*")
public class ConfiguracionCorreoController {

    @Autowired
    private ConfiguracionCorreoService service;

    @PostMapping("/{reporteId}")
    public ConfiguracionCorreo guardar(
            @PathVariable Integer reporteId,
            @RequestBody ConfiguracionCorreo config) {

        return service.guardar(reporteId, config);
    }

    @GetMapping("/{reporteId}")
    public ConfiguracionCorreo obtener(@PathVariable Integer reporteId) {
        return service.obtener(reporteId);
    }
}