package ar.com.marcospaez.cursos.repository;

import ar.com.marcospaez.cursos.entity.Usuario;
import ar.com.marcospaez.cursos.enumeraciones.Rol;
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
public class UsuarioRepositoryTests {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Test para guardar un usuario")
    void testGuardarUsuario() {
        Rol rol = Rol.ADMIN;
        Usuario usuario1 = new Usuario(1L, "admin", "admin@gmail.com", "password1", rol);

        Usuario usuarioGuardado = usuarioRepository.save(usuario1);

        assertThat(usuarioGuardado).isNotNull();
    }

    @Test
    @DisplayName("Test para lista usuarios")
    void testListarUsuarios() {
        Rol rol = Rol.USER;
        Usuario usuario1 = new Usuario(2L, "admin", "admin@gmail.com", "password1", rol);
        usuarioRepository.save(usuario1);

        List<Usuario> listaUsuarios = usuarioRepository.findAll();

        assertThat(listaUsuarios).isNotNull();
        assertThat(listaUsuarios.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Test para obtener un usuario por id")
    void testObtenerUsuarioPorId() {
        Usuario usuarioBD = usuarioRepository.findById(1L).get();

        assertThat(usuarioBD).isNotNull();
    }

    @Test
    @DisplayName("Test para actualizar un usuario")
    void testActualizarUsuario() {
        Usuario usuarioGuardado = usuarioRepository.findById(1L).get();
        usuarioGuardado.setNombre("Pepe");
        usuarioGuardado.setPassword("456789");
        Usuario usuarioActualizado = usuarioRepository.save(usuarioGuardado);

        assertThat(usuarioActualizado.getNombre()).isEqualTo("Pepe");
        assertThat(usuarioActualizado.getPassword()).isEqualTo("456789");
    }

    @Test
    @DisplayName("Test para eliminar un usuario")
    void testEliminarUsuario() {
        usuarioRepository.deleteById(1L);
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(1L);

        assertThat(usuarioOptional).isEmpty();
    }
}
