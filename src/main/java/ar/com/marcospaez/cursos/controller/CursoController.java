package ar.com.marcospaez.cursos.controller;

import ar.com.marcospaez.cursos.entity.Curso;
import ar.com.marcospaez.cursos.entity.Profesor;
import ar.com.marcospaez.cursos.service.CursoService;
import ar.com.marcospaez.cursos.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private ProfesorService profesorService;

    @GetMapping(value = "/cursos")
    public String cursos(Model model) {
        try {
            List<Curso> cursos = this.cursoService.getCursos();
            model.addAttribute("cursos", cursos);
            return "views/cursos";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping(value = "/formulario/cursonuevo")
    public String formularioClubNuevo(Long id_curso, Model model) {
        try {
            model.addAttribute("listaProfesores", this.profesorService.getProfesores());
            model.addAttribute("curso", new Curso());
            return "views/forms/cursonuevo";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping(value = "/formulario/cursonuevo")
    public String nuevoCurso(Curso curso) {
        try {
            this.cursoService.saveCurso(curso);
            return "redirect:/cursos";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping(value = "/formulario/curso/{id_curso}")
    public String formularioCurso(@PathVariable("id_curso") Long id_curso, Model model) {
        try {
            model.addAttribute("listaProfesores", this.profesorService.getProfesores());
            model.addAttribute("curso", this.cursoService.findCurso(id_curso));
            return "views/forms/curso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping(value = "/formulario/curso/{id_curso}")
    public String guardarCurso(@PathVariable("id_curso") Long id_curso,
                               Curso curso,
                               @RequestParam("nombre") String nombre,
                               @RequestParam("descripcion") String descripcion,
                               @RequestParam("turno") String turno,
                               @RequestParam("unProfesor") Profesor unProfesor) {
        try {
            this.cursoService.editCurso(id_curso, nombre, descripcion, turno, unProfesor);
            return "redirect:/cursos";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping(value = "/eliminar/curso/{id_curso}")
    public String eliminarCurso(@PathVariable("id_curso") Long id_curso, Model model) {
        try {
            this.cursoService.deleteCurso(id_curso);
            return "redirect:/cursos";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
