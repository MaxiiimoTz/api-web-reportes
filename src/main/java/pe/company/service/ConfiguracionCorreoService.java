package pe.company.service;

import pe.company.model.ConfiguracionCorreo;

public interface ConfiguracionCorreoService {

    ConfiguracionCorreo guardar(Integer reporteId, ConfiguracionCorreo config);

    ConfiguracionCorreo obtener(Integer reporteId);

}