package pe.company.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pe.company.model.Programacion;
import pe.company.model.ReporteAsignado;
import pe.company.repository.EnvioRepository;
import pe.company.repository.ProgramacionRepository;
import pe.company.repository.ReporteAsignadoRepository;
import pe.company.service.EnvioService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class SchedulerService {

    @Autowired
    private ProgramacionRepository programacionRepository;

    @Autowired
    private ReporteAsignadoRepository asignadoRepository;

    @Autowired
    private EnvioRepository envioRepository;

    @Autowired
    private EnvioService envioService;

    // 🔥 corre cada minuto
    @Scheduled(cron = "0 * * * * *")
    public void ejecutarEnviosAutomaticos() {

        List<Programacion> programaciones = programacionRepository.findAll();

        LocalTime ahora = LocalTime.now();

        for (Programacion p : programaciones) {

            if (p.getActivo() == null || !p.getActivo()) continue;

            // 🔥 valida hora exacta
            if (!p.getHora().equals(ahora.getHour()) ||
                !p.getMinuto().equals(ahora.getMinute())) continue;

            Integer reporteId = p.getReporte().getId();

            // 🔥 obtener analistas asignados
            List<ReporteAsignado> asignaciones =
                    asignadoRepository.findByReporte_Id(reporteId);

            for (ReporteAsignado a : asignaciones) {

                Integer usuarioId = a.getUsuario().getId();

                // 🔥 evitar duplicado (ya enviado hoy)
                if (yaEnviadoHoy(reporteId)) {
                    continue;
                }

                envioService.enviarManual(reporteId, usuarioId);
            }
        }
    }

    // 🔥 VALIDACIÓN PRO
    private boolean yaEnviadoHoy(Integer reporteId) {

        LocalDate hoy = LocalDate.now();

        LocalDateTime inicio = hoy.atStartOfDay();
        LocalDateTime fin = hoy.atTime(23, 59, 59);

        List<?> envios = envioRepository
                .findByReporte_IdAndFechaEnvioBetween(reporteId, inicio, fin);

        return !envios.isEmpty();
    }
}