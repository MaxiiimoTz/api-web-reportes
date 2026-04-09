package pe.company.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.company.dto.CumplimientoDTO;
import pe.company.model.Envio;
import pe.company.model.Programacion;
import pe.company.model.ReporteAsignado;
import pe.company.repository.EnvioRepository;
import pe.company.repository.ProgramacionRepository;
import pe.company.repository.ReporteAsignadoRepository;
import pe.company.repository.UsuarioRepository;
import pe.company.service.CumplimientoService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CumplimientoServiceImpl implements CumplimientoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ReporteAsignadoRepository asignadoRepository;

    @Autowired
    private ProgramacionRepository programacionRepository;

    @Autowired
    private EnvioRepository envioRepository;

    @Override
    public List<CumplimientoDTO> obtenerDashboard() {

        List<CumplimientoDTO> resultado = new ArrayList<>();

        usuarioRepository.findAll().forEach(usuario -> {

            CumplimientoDTO dto = new CumplimientoDTO();

            dto.setUsuarioId(usuario.getId());
            dto.setNombre(usuario.getNombre());

            int aTiempo = 0;
            int tarde = 0;
            int noEnviado = 0;

            List<ReporteAsignado> asignaciones =
                    asignadoRepository.findByUsuario_Id(usuario.getId());

            for (ReporteAsignado a : asignaciones) {

                Integer reporteId = a.getReporte().getId();

                Programacion prog = programacionRepository.findByReporte_Id(reporteId);

                if (prog == null || !prog.getActivo()) continue;

                LocalDate hoy = LocalDate.now();

                LocalDateTime inicio = hoy.atStartOfDay();
                LocalDateTime fin = hoy.atTime(23, 59, 59);

                List<Envio> envios = envioRepository
                        .findByReporte_IdAndFechaEnvioBetween(reporteId, inicio, fin);

                if (envios.isEmpty()) {
                    noEnviado++;
                } else {
                    LocalDateTime fechaEnvio = envios.get(0).getFechaEnvio();

                    LocalDateTime horaProgramada =
                            hoy.atTime(prog.getHora(), prog.getMinuto());

                    if (fechaEnvio.isBefore(horaProgramada.plusMinutes(5))) {
                        aTiempo++;
                    } else {
                        tarde++;
                    }
                }
            }

            int total = aTiempo + tarde + noEnviado;

            double porcentaje = total == 0 ? 0 : ((double) aTiempo / total) * 100;

            dto.setEnviadosATiempo(aTiempo);
            dto.setEnviadosTarde(tarde);
            dto.setNoEnviados(noEnviado);
            dto.setPorcentaje(porcentaje);

            resultado.add(dto);
        });

        return resultado;
    }
}