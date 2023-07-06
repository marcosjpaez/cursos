package ar.com.marcospaez.cursos.repository;

import ar.com.marcospaez.cursos.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICursoRepistory extends JpaRepository<Curso, Long> {
}
