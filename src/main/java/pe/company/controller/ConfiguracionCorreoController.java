package pe.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.company.model.*;
import pe.company.repository.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/configuracion")
@CrossOrigin("*")
public class ConfiguracionCorreoController {

    @Autowired
    private ConfiguracionCorreoRepository configRepository;

    @Autowired
    private DestinatarioRepository destinatarioRepository;

    @Autowired
    private AdjuntoRepository adjuntoRepository;

    @GetMapping("/{reporteId}")
    public Map<String, Object> obtenerTodo(@PathVariable Integer reporteId) {

        ConfiguracionCorreo config =
                configRepository.findByReporte_Id(reporteId);

        List<Destinatario> destinatarios =
                destinatarioRepository.findByReporte_Id(reporteId);

        List<Adjunto> adjuntos =
                adjuntoRepository.findByReporte_Id(reporteId);

        return Map.of(
                "asunto", config != null ? config.getAsunto() : "",
                "mensaje", config != null ? config.getMensaje() : "",

                "destinatarios",
                destinatarios.stream()
                        .filter(d -> d.getTipo().name().equals("PARA"))
                        .map(Destinatario::getEmail)
                        .toList(),

                "copias",
                destinatarios.stream()
                        .filter(d -> d.getTipo().name().equals("CC"))
                        .map(Destinatario::getEmail)
                        .toList(),

                "adjuntos", adjuntos
        );
    }
}