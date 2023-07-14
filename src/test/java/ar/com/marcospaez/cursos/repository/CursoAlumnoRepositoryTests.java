package ar.com.marcospaez.cursos.repository;

import ar.com.marcospaez.cursos.entity.Alumno;
import ar.com.marcospaez.cursos.entity.Curso;
import ar.com.marcospaez.cursos.entity.CursoAlumno;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CursoAlumnoRepositoryTests {

    @Autowired
    private ICursoAlumnoRepository cursoAlumnoRepository;

    @Autowired
    private IAlumnoRepository alumnoRepository;

    @Autowired
    private ICursoRepistory cursoRepistory;

    @Test
    @DisplayName("Test para guardar un curso por alumno")
    void testGuardarCursoAlumno() {
        Alumno alumno = alumnoRepository.findById(1L).get();
        Curso curso = cursoRepistory.findById(1L).get();
        CursoAlumno cursoAlumno1 = new CursoAlumno();
        cursoAlumno1.setUnAlumno(alumno);
        cursoAlumno1.setUnCurso(curso);

        CursoAlumno cursoAlumnoGuardado = cursoAlumnoRepository.save(cursoAlumno1);

        assertThat(cursoAlumnoGuardado).isNotNull();
        assertThat(cursoAlumnoGuardado.getId_cursoalumno()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test para listar cursos por alumno")
    void testListarCursosAlumno() {
        Alumno alumno = alumnoRepository.findById(1L).get();
        Curso curso = cursoRepistory.findById(1L).get();
        CursoAlumno cursoAlumno1 = new CursoAlumno();
        cursoAlumno1.setUnAlumno(alumno);
        cursoAlumno1.setUnCurso(curso);
        CursoAlumno cursoAlumnoGuardado = cursoAlumnoRepository.save(cursoAlumno1);

        List<CursoAlumno> listaCursosAlumno = cursoAlumnoRepository.findAll();

        assertThat(listaCursosAlumno).isNotNull();
        assertThat(listaCursosAlumno.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Test para obtener curso por alumno")
    void testObtenerCursoAlumno() {
        CursoAlumno cursoAlumnoBD = cursoAlumnoRepository.findById(1L).get();

        assertThat(cursoAlumnoBD).isNotNull();
    }

    @Test
    @DisplayName("Test para eliminar curso por alumno")
    void testEliminarCursoAlumno() {
        cursoAlumnoRepository.deleteById(1L);

        Optional<CursoAlumno> cursoAlumnoOptional = cursoAlumnoRepository.findById(1L);

        assertThat(cursoAlumnoOptional).isEmpty();
    }
}
