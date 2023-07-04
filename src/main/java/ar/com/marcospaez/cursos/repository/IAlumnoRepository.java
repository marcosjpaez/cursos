package ar.com.marcospaez.cursos.repository;

import ar.com.marcospaez.cursos.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAlumnoRepository extends JpaRepository<Alumno, Long> {
}
