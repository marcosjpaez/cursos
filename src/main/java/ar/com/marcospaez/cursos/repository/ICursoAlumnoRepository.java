package ar.com.marcospaez.cursos.repository;

import ar.com.marcospaez.cursos.entity.CursoAlumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICursoAlumnoRepository extends JpaRepository<CursoAlumno, Long> {

    @Query(value = "SELECT * FROM curso_alumno WHERE id_alumno = :id_alumno", nativeQuery = true)
    List<CursoAlumno> buscarPorAlumno(Long id_alumno);

    @Query(value = "SELECT * FROM curso_alumno WHERE id_curso = :id_curso", nativeQuery = true)
    List<CursoAlumno> buscarPorCurso(Long id_curso);

    @Query(value = "SELECT * FROM curso_alumno WHERE id_curso = :id_curso AND id_alumno = (SELECT id_alumno FROM alumno WHERE nombre LIKE %:nombre%)", nativeQuery = true)
    List<CursoAlumno> buscarPorNombre(Long id_curso, String nombre);

}
