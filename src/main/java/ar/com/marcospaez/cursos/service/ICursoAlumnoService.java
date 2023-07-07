package ar.com.marcospaez.cursos.service;

import ar.com.marcospaez.cursos.entity.CursoAlumno;
import ar.com.marcospaez.cursos.exceptions.MiException;

import java.util.List;

public interface ICursoAlumnoService {

    public List<CursoAlumno> getCursoAlumno();

    public void saveCursoAlumno(CursoAlumno cursoAlumno) throws MiException;

    public void deleteCursoAlumno(Long id_cursoalumno);

    public CursoAlumno findCursoAlumno(long id_cursoalumno);

    public List<CursoAlumno> findByAlumno(Long id_alumno);

    public List<CursoAlumno> findByCurso(Long id_curso);

    public List<CursoAlumno> findByNombre(Long id_curso, String nombre);
}
