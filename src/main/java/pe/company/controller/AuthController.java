package pe.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.company.dto.LoginResponse;
import pe.company.model.Usuario;
import pe.company.service.AuthService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> optionsLogin() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {

        String username = body.get("username");
        String password = body.get("password");

        Usuario user = authService.login(username, password);

        if (user == null) {
            return ResponseEntity
                    .status(401)
                    .body(new LoginResponse(false, "Credenciales incorrectas", null));
        }

        return ResponseEntity.ok(
                new LoginResponse(true, "Login exitoso", user)
        );
    }
}