package ar.com.marcospaez.cursos.controller;

import ar.com.marcospaez.cursos.entity.Profesor;
import ar.com.marcospaez.cursos.exceptions.MiException;
import ar.com.marcospaez.cursos.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;
    @GetMapping(value = "/profesores")
    public String profesores(Model model) {
        try {
            List<Profesor> profesores = this.profesorService.getProfesores();
            model.addAttribute("profesores", profesores);
            return "views/profesores";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping(value = "/formulario/profesornuevo")
    public String formularioProfesorNuevo(Long id_profesor, Model model) {
        try {
            model.addAttribute("profesor", new Profesor());
            return "views/forms/profesornuevo";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping(value = "/formulario/profesornuevo")
    public String nuevoProfesor(Profesor profesor, ModelMap modelo) {
        try {
            this.profesorService.saveProfesor(profesor);
            return "redirect:/profesores";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            modelo.addAttribute("profesor", new Profesor());
            return "/views/forms/profesornuevo";
        }
    }
    @GetMapping(value = "/formulario/profesor/{id_profesor}")
    public String formularioProfesores(@PathVariable("id_profesor") Long id_profesor, Model model) {
        try {
            model.addAttribute("profesor", this.profesorService.findProfesor(id_profesor));
            return "views/forms/profesor";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping(value = "/formulario/profesor/{id_profesor}")
    public String guardarProfesor(@PathVariable("id_profesor") Long id_profesor,
                                  Profesor profesor,
                                  @RequestParam("nombre") String nombre,
                                  @RequestParam("email") String email,
                                  ModelMap modelo) {
        try {
            this.profesorService.editProfesor(id_profesor, nombre, email);
            return "redirect:/profesores";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "/views/forms/profesor";

        }
    }

    @GetMapping(value = "/eliminar/profesor/{id_profesor}")
    public String eliminarProfesor(@PathVariable("id_profesor") Long id_profesor, Model model) {
        try {
            this.profesorService.deleteProfesor(id_profesor);
            return "redirect:/profesores";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
