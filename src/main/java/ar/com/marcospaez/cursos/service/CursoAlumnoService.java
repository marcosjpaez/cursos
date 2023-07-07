package ar.com.marcospaez.cursos.service;

import ar.com.marcospaez.cursos.entity.CursoAlumno;
import ar.com.marcospaez.cursos.exceptions.MiException;
import ar.com.marcospaez.cursos.repository.ICursoAlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoAlumnoService implements ICursoAlumnoService{

    @Autowired
    private ICursoAlumnoRepository cursoAlumnoRepository;
    @Override
    public List<CursoAlumno> getCursoAlumno() {
        List<CursoAlumno> listaCursoAlumno = cursoAlumnoRepository.findAll();
        return listaCursoAlumno;
    }

    @Override
    public void saveCursoAlumno(CursoAlumno cursoAlumno) throws MiException {
        if(cursoAlumno.getUnCurso()==null) {
            throw new MiException("el nombre no puede estar vacío");
        }
        cursoAlumnoRepository.save(cursoAlumno);
    }

    @Override
    public void deleteCursoAlumno(Long id_cursoalumno) {
        cursoAlumnoRepository.deleteById(id_cursoalumno);
    }

    @Override
    public CursoAlumno findCursoAlumno(long id_cursoalumno) {
        CursoAlumno cursoAlumno = cursoAlumnoRepository.findById(id_cursoalumno).orElse(null);
        return cursoAlumno;
    }

    @Override
    public List<CursoAlumno> findByAlumno(Long id_alumno) {
        List<CursoAlumno> cursosAlumnos = cursoAlumnoRepository.buscarPorAlumno(id_alumno);
        return cursosAlumnos;
    }

    @Override
    public List<CursoAlumno> findByCurso(Long id_curso) {
        List<CursoAlumno> alumnosCurso = cursoAlumnoRepository.buscarPorCurso(id_curso);
        return alumnosCurso;
    }

    @Override
    public List<CursoAlumno> findByNombre(Long id_curso, String nombre) {
        List<CursoAlumno> alumnosCurso = cursoAlumnoRepository.buscarPorNombre(id_curso, nombre);
        return alumnosCurso;
    }
}
