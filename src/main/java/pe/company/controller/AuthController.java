package pe.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.company.model.Usuario;
import pe.company.service.AuthService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Usuario login(@RequestBody Map<String, String> body) {

        return authService.login(
                body.get("username"),
                body.get("password")
        );
    }
}