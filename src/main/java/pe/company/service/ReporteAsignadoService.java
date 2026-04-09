package pe.company.service;

import pe.company.model.ReporteAsignado;

import java.util.List;

public interface ReporteAsignadoService {

    ReporteAsignado asignar(Integer reporteId, Integer usuarioId);

    List<ReporteAsignado> listarPorUsuario(Integer usuarioId);

}