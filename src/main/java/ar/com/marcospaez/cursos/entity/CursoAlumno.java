package ar.com.marcospaez.cursos.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class CursoAlumno {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_cursoalumno;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso unCurso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_alumno", nullable = false)
    private Alumno unAlumno;

    public CursoAlumno() {
    }

}
