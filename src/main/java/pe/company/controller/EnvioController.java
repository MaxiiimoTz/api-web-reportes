package pe.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.company.model.Envio;
import pe.company.service.EnvioService;

import java.util.Map;

@RestController
@RequestMapping("/api/envios")
@CrossOrigin("*")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @PostMapping("/manual")
    public Envio enviar(@RequestBody Map<String, Integer> body) {

        return envioService.enviarManual(
                body.get("reporteId"),
                body.get("usuarioId")
        );
    }
}