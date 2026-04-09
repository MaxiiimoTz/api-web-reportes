package pe.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.company.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUsername(String username);

}