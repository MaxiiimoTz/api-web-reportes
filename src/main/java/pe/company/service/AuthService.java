package pe.company.service;

import pe.company.model.Usuario;

public interface AuthService {

    Usuario login(String username, String password);

}