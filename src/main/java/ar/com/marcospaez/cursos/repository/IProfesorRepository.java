package ar.com.marcospaez.cursos.repository;

import ar.com.marcospaez.cursos.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfesorRepository extends JpaRepository<Profesor, Long> {
}
