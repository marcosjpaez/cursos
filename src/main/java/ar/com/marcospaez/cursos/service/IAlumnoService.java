package ar.com.marcospaez.cursos.service;

import ar.com.marcospaez.cursos.entity.Alumno;
import ar.com.marcospaez.cursos.entity.Curso;
import ar.com.marcospaez.cursos.exceptions.MiException;

import java.time.LocalDate;
import java.util.List;

public interface IAlumnoService {

    public List<Alumno> getAlumnos();

    public void saveAlumno(Alumno alumno) throws MiException;

    public void deleteAlumno(Long id_alumno);

    public Alumno findAlumno(Long id_alumno);

    public void editAlumno(Long id_alumno, String nuevoNombre, String nuevoEmail, String nuevoDni, LocalDate nuevaFechaNac) throws MiException;
}
