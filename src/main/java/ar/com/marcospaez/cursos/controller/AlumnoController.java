package ar.com.marcospaez.cursos.controller;

import ar.com.marcospaez.cursos.entity.Alumno;
import ar.com.marcospaez.cursos.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping(value = "/alumnos")
    public String alumnos(Model model) {
        try {
            List<Alumno> alumnos = this.alumnoService.getAlumnos();
            model.addAttribute("alumnos", alumnos);
            return "views/alumnos";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping(value = "/formulario/alumnonuevo")
    public String formularioAlumnoNuevo(Long id_alumno, Model model) {
        try {
            model.addAttribute("alumno", new Alumno());
            return "views/forms/alumnonuevo";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping(value = "/formulario/alumnonuevo")
    public String nuevoAlumno(Alumno alumno) {
        try {
            this.alumnoService.saveAlumno(alumno);
            return "redirect:/alumnos";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping(value = "/formulario/alumno/{id_alumno}")
    public String formularioAlumno(@PathVariable("id_alumno") Long id_alumno, Model model) {
        try {
            model.addAttribute("alumno", this.alumnoService.findAlumno(id_alumno));
            return "views/forms/alumno";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping(value = "/formulario/alumno/{id_alumno}")
    public String guardarAlumno(@PathVariable("id_alumno") Long id_alumno,
                                Alumno alumno,
                                @RequestParam("nombre") String nombre,
                                @RequestParam("email") String email,
                                @RequestParam("dni") String dni,
                                @RequestParam("fechaNac") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaNac) {
        try {
            this.alumnoService.editAlumno(id_alumno, nombre, email, dni, fechaNac);
            return "redirect:/alumnos";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping(value = "/eliminar/alumno/{id_alumno}")
    public String eliminarAlumno(@PathVariable("id_alumno") Long id_alumno, Model model) {
        try {
            this.alumnoService.deleteAlumno(id_alumno);
            return "redirect:/alumnos";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
