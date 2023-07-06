package ar.com.marcospaez.cursos.controller;

import ar.com.marcospaez.cursos.entity.Usuario;
import ar.com.marcospaez.cursos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class PrincipalController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "registro";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String password2,
                           Model model) {
        try {
            usuarioService.resgistrar(nombre, email, password, password2);
            return "index";
        } catch (Exception e) {
            return "registro";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String inicio(HttpSession sesion, Model model) {
        Usuario logueado = (Usuario) sesion.getAttribute("usuariosession");
        if(logueado.getRol().toString().equals("ADMIN")) {
            return "index";
        }
        return "index";

    }
}
