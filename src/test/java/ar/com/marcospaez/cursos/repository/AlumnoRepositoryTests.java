package ar.com.marcospaez.cursos.repository;

import static org.assertj.core.api.Assertions.assertThat;

import ar.com.marcospaez.cursos.entity.Alumno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AlumnoRepositoryTests {

    @Autowired
    private IAlumnoRepository alumnoRepository;

    private Alumno alumno;

    @Test
    @DisplayName("Test para guardar un alumno")
    void testGuardarAlumno() {
        Alumno alumno1 = new Alumno(1L, "Juan", "22222222", "juan@gmail.com", LocalDate.now());

        Alumno alumnoGuardado = alumnoRepository.save(alumno1);

        assertThat(alumnoGuardado).isNotNull();
        assertThat(alumnoGuardado.getId_alumno()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test para listar alumnos")
    void testListarAlumnos() {
        Alumno alumno1 = new Alumno(2L, "Juan", "22222222", "juan@gmail.com", LocalDate.now());
        alumnoRepository.save(alumno1);

        List<Alumno> listaAlumnos = alumnoRepository.findAll();

        assertThat(listaAlumnos).isNotNull();
        assertThat(listaAlumnos.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Test para obtener un alumno por id")
    void testObtenerAlumnoPorId() {
        Alumno alumnoBD = alumnoRepository.findById(1L).get();

        assertThat(alumnoBD).isNotNull();
    }

    @Test
    @DisplayName("Test para actualizar un alumno")
    void testActualizarAlumno() {
        Alumno alumnoGuardado = alumnoRepository.findById(1L).get();
        alumnoGuardado.setNombre("Juan");
        alumnoGuardado.setEmail("juan@gmail.com");
        alumnoGuardado.setDni("11111111");
        alumnoGuardado.setFechaNac(LocalDate.now());

        Alumno alumnoActualizado = alumnoRepository.save(alumnoGuardado);

        assertThat(alumnoActualizado.getNombre()).isEqualTo("Juan");
        assertThat(alumnoActualizado.getEmail()).isEqualTo("juan@gmail.com");
        assertThat(alumnoActualizado.getDni()).isEqualTo("11111111");
    }

    @Test
    @DisplayName("Test para eliminar un alumno")
    void testEliminarAlumno() {
        alumnoRepository.deleteById(1L);

        Optional<Alumno> alumnoOptional = alumnoRepository.findById(1L);

        assertThat(alumnoOptional).isEmpty();
    }
}
