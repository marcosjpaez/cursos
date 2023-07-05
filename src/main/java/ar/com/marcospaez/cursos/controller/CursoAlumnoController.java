package ar.com.marcospaez.cursos.controller;

import ar.com.marcospaez.cursos.entity.CursoAlumno;
import ar.com.marcospaez.cursos.service.AlumnoService;
import ar.com.marcospaez.cursos.service.CursoAlumnoService;
import ar.com.marcospaez.cursos.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CursoAlumnoController {

    @Autowired
    private CursoAlumnoService cursoAlumnoService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping(value = "/alumno/curso/{id_alumno}")
    public String cursosPorAlumno(@PathVariable("id_alumno") Long id_alumno, Model model) {
        try {
            model.addAttribute("alumno", this.alumnoService.findAlumno(id_alumno));
            model.addAttribute("listaCursos", this.cursoService.getCursos());
            List<CursoAlumno> cursosAlumno = this.cursoAlumnoService.findByAlumno(id_alumno);
            model.addAttribute("cursosAlumno", cursosAlumno);
            return "views/cursoalumno";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping(value = "/formulario/cursoalumnonuevo/{id_alumno}")
    public String cursoAlumnoNuevo(@PathVariable("id_alumno") Long id_alumno, Model model) {
        try {
            model.addAttribute("listaCursos", this.cursoService.getCursos());
            model.addAttribute("cursoalumno", new CursoAlumno());
            return "views/forms/cursoalumnonuevo";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping(value = "/formulario/cursoalumnonuevo/{id_alumno}")
    public String nuevoCursoAlumno(@PathVariable("id_alumno") Long id_alumno, CursoAlumno cursoAlumno) {
        try {
            cursoAlumno.setUnAlumno(this.alumnoService.findAlumno(id_alumno));
            this.cursoAlumnoService.saveCursoAlumno(cursoAlumno);
            return "redirect:/alumno/curso/"+id_alumno;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping(value = "/alumno/curso/eliminar/cursoalumno/{id_cursoalumno}/{id_alumno}")
    public String eliminarCursoAlumno(@PathVariable("id_cursoalumno") Long id_cursoalumno,
                                      @PathVariable("id_alumno") Long id_alumno,
                                      Model model) {
        try {
            this.cursoAlumnoService.deleteCursoAlumno(id_cursoalumno);
            return "redirect:/alumno/curso/"+id_alumno;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping(value = "/curso/alumno/{id_curso}")
    public String alumnosPorCurso(@PathVariable("id_curso") Long id_curso, Model model) {
        try {
            model.addAttribute("curso", this.cursoService.findCurso(id_curso));
            model.addAttribute("listaAlumnos", this.alumnoService.getAlumnos());
            List<CursoAlumno> cursosAlumno = this.cursoAlumnoService.findByCurso(id_curso);
            model.addAttribute("cursosAlumno", cursosAlumno);
            return "views/alumnocurso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping(value = "/formulario/busquedaalumnocurso/{id_curso}")
    public String buscarPorAlumno(@PathVariable("id_curso") Long id_curso,
                                  @RequestParam("nombre") String nombre,
                                  Model model) {
        try {
            model.addAttribute("curso", this.cursoService.findCurso(id_curso));
            model.addAttribute("curso", this.cursoService.findCurso(id_curso));
            model.addAttribute("listaAlumnos", this.alumnoService.getAlumnos());
            List<CursoAlumno> cursosAlumno = this.cursoAlumnoService.findByNombre(id_curso, nombre);
            model.addAttribute("cursosAlumno", cursosAlumno);
            return "views/nombrealumnocurso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
