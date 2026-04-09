package pe.company.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.company.model.*;
import pe.company.repository.*;
import pe.company.service.EnvioService;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.internet.MimeMessage;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnvioServiceImpl implements EnvioService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DestinatarioRepository destinatarioRepository;

    @Autowired
    private ConfiguracionCorreoRepository configRepository;

    @Autowired
    private EnvioRepository envioRepository;

    @Autowired
    private AdjuntoRepository adjuntoRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Envio enviarManual(Integer reporteId, Integer usuarioId) {

        Reporte reporte = reporteRepository.findById(reporteId)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Destinatario> destinatarios = destinatarioRepository.findByReporte_Id(reporteId);
        ConfiguracionCorreo config = configRepository.findByReporte_Id(reporteId);
        List<Adjunto> adjuntos = adjuntoRepository.findByReporte_Id(reporteId); // 🔥 FALTABA ESTO

        if (destinatarios.isEmpty()) {
            throw new RuntimeException("No hay destinatarios configurados");
        }

        if (config == null) {
            throw new RuntimeException("No hay configuración de correo");
        }

        // ENVÍO REAL CON ADJUNTOS
        enviarCorreoConAdjuntos(destinatarios, config, adjuntos);

        Envio envio = new Envio();
        envio.setReporte(reporte);
        envio.setUsuario(usuario);
        envio.setFechaEnvio(LocalDateTime.now());
        envio.setTipo(TipoEnvio.MANUAL);
        envio.setEstado(EstadoEnvio.ENVIADO);

        return envioRepository.save(envio);
    }

    private void enviarCorreoConAdjuntos(List<Destinatario> destinatarios,
                                         ConfiguracionCorreo config,
                                         List<Adjunto> adjuntos) {

        try {

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // asunto y mensaje
            helper.setSubject(config.getAsunto());
            helper.setText(config.getMensaje(), false);

            // destinatarios
            String[] correos = destinatarios.stream()
                    .map(Destinatario::getEmail)
                    .toArray(String[]::new);

            helper.setTo(correos);

            for (Adjunto adj : adjuntos) {

                File file = new File(adj.getRuta());

                if (file.exists()) {
                    helper.addAttachment(adj.getNombre(), new FileSystemResource(file));
                }
            }

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Error enviando correo: " + e.getMessage());
        }
    }
}