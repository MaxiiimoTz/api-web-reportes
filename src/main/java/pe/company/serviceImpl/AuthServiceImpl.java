package pe.company.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pe.company.model.Usuario;
import pe.company.repository.UsuarioRepository;
import pe.company.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Usuario login(String username, String password) {

        Usuario user = usuarioRepository.findByUsername(username);

        if (user == null) {
            return null;
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return null;
        }

        return user;
    }
}