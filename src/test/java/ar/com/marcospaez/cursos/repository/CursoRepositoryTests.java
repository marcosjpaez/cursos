package ar.com.marcospaez.cursos.repository;

import static org.assertj.core.api.Assertions.assertThat;

import ar.com.marcospaez.cursos.entity.Curso;
import ar.com.marcospaez.cursos.entity.Profesor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CursoRepositoryTests {

    @Autowired
    private ICursoRepistory cursoRepistory;

    @Autowired
    private IProfesorRepository profesorRepository;

    @Test
    @DisplayName("Test para guardar un curso")
    void testGuardarCurso() {
        Profesor profesorBD = profesorRepository.findById(1L).get();
        Curso curso1 = new Curso(1L, "Curso1", "Descripción1", "Mañana", profesorBD);

        Curso cursoGuardado = cursoRepistory.save(curso1);

        assertThat(cursoGuardado).isNotNull();
        assertThat(cursoGuardado.getId_curso()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test para listar cursos")
    void testListarCursos() {
        Profesor profesorBD = profesorRepository.findById(1L).get();
        Curso curso1 = new Curso(2L, "Curso2", "Descripción2", "Tarde", profesorBD);
        cursoRepistory.save(curso1);

        List<Curso> listaCursos = cursoRepistory.findAll();

        assertThat(listaCursos).isNotNull();
        assertThat(listaCursos.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Test para obtener un curso por id")
    void testObtenerCursoPorId() {
        Curso cursoBD = cursoRepistory.findById(1L).get();

        assertThat(cursoBD).isNotNull();
    }

    @Test
    @DisplayName("Test para actualizar un curso")
    void testActualizarCurso() {
        Curso cursoGuardado = cursoRepistory.findById(1L).get();
        cursoGuardado.setNombre("Curso nuevo");
        cursoGuardado.setTurno("Noche");

        Curso cursoActualizado = cursoRepistory.save(cursoGuardado);

        assertThat(cursoActualizado.getNombre()).isEqualTo("Curso nuevo");
        assertThat(cursoActualizado.getTurno()).isEqualTo("Noche");
    }

    @Test
    @DisplayName("Test para eliminar un curso")
    void testEliminarCurso() {
        cursoRepistory.deleteById(1L);

        Optional<Curso> cursoOptional = cursoRepistory.findById(1L);

        assertThat(cursoOptional).isEmpty();
    }
}
