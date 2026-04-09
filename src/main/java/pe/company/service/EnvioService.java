package pe.company.service;

import pe.company.model.Envio;

public interface EnvioService {

    Envio enviarManual(Integer reporteId, Integer usuarioId);

}