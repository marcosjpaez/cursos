package ar.com.marcospaez.cursos.repository;

import static org.assertj.core.api.Assertions.assertThat;

import ar.com.marcospaez.cursos.entity.Profesor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProfesorRepositoryTest {

    @Autowired
    private IProfesorRepository profesorRepository;

    private Profesor profesor;

    @BeforeEach
    void Setup() {
        profesor = new Profesor(1L,"Profesor1", "profe1@gmail.com");
    }

    @DisplayName("Test para guardar un profesor")
    @Test
    void testGuardarProfesor() {
        //given
        Profesor profesor1 = new Profesor(2L,"Profesor2", "profe2@gmail.com");

        //when
        Profesor profesorGuardado = profesorRepository.save(profesor1);

        //then
        assertThat(profesorGuardado).isNotNull();
        assertThat(profesorGuardado.getId_profesor()).isGreaterThan(0);
    }

    @DisplayName("Test para listar profesores")
    @Test
    void testListarProfesores() {
        //give
        Profesor profesor1 = new Profesor(2L,"Profesor2", "profe2@gmail.com");
        profesorRepository.save(profesor1);
        profesorRepository.save(profesor);

        //when
        List<Profesor> listaProfesores =profesorRepository.findAll();


        //then
        assertThat(listaProfesores).isNotNull();
        assertThat(listaProfesores.size()).isEqualTo(2);
    }

    @DisplayName("Test para obtener un profesor por id")
    @Test
    void testObtenerProfesorPorId() {

        Profesor profesorBD = profesorRepository.findById(1L).get();

        assertThat(profesorBD).isNotNull();

    }

    @DisplayName("Test para actualizar un profesor")
    @Test
    void testActualizarProfesor(){
        Profesor profesorGuardado = profesorRepository.findById(1L).get();
        profesorGuardado.setNombre("Otro Profesor");
        profesorGuardado.setEmail("otro@gmail.com");
        Profesor profesorActualizado = profesorRepository.save(profesorGuardado);

        //then
        assertThat(profesorActualizado.getEmail()).isEqualTo("otro@gmail.com");
        assertThat(profesorActualizado.getNombre()).isEqualTo("Otro Profesor");

    }

    @DisplayName("Test para eliminar un profesor")
    @Test
    void testElimiarProfesor() {

        profesorRepository.deleteById(1L);
        Optional<Profesor> profesorOptional = profesorRepository.findById(1L);

        //then
        assertThat(profesorOptional).isEmpty();

    }
}
