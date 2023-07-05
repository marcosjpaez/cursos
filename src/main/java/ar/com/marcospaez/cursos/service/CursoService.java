package ar.com.marcospaez.cursos.service;

import ar.com.marcospaez.cursos.entity.Curso;
import ar.com.marcospaez.cursos.entity.Profesor;
import ar.com.marcospaez.cursos.repository.ICursoRepistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService implements ICursoService {

    @Autowired
    private ICursoRepistory cursoRepistory;

    @Override
    public List<Curso> getCursos() {
        List<Curso> listaCursos = cursoRepistory.findAll();
        return listaCursos;
    }

    @Override
    public void saveCurso(Curso curso) {
        cursoRepistory.save(curso);
    }

    @Override
    public void deleteCurso(Long id_curso) {
        cursoRepistory.deleteById(id_curso);
    }

    @Override
    public Curso findCurso(Long id_curso) {
        Curso curso = cursoRepistory.findById(id_curso).orElse(null);
        return curso;
    }

    @Override
    public void editCurso(Long id_curso, String nuevoNombre, String nuevaDescripcion, String nuevoTurno, Profesor unProfesor) {
        Curso curso = this.findCurso(id_curso);
        curso.setNombre(nuevoNombre);
        curso.setDescripcion(nuevaDescripcion);
        curso.setTurno(nuevoTurno);
        curso.setUnProfesor(unProfesor);
        this.saveCurso(curso);
    }
}
