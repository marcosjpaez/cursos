package ar.com.marcospaez.cursos.service;

import ar.com.marcospaez.cursos.entity.Curso;
import ar.com.marcospaez.cursos.entity.Profesor;

import java.util.List;

public interface ICursoService {

    public List<Curso> getCursos();

    public void saveCurso(Curso curso);

    public void deleteCurso(Long id_curso);

    public Curso findCurso(Long id_curso);

    public void editCurso(Long id_curso, String nuevoNombre, String nuevaDescripcion, String nuevoTurno, Profesor unProfesor);
}
