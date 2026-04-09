package pe.company.service;

import pe.company.model.Reporte;

import java.util.List;

public interface ReporteService {

    List<Reporte> listar();

    Reporte crear(Reporte reporte);
    
    Reporte actualizar(int id, Reporte reporte);

}