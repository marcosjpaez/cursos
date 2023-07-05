package ar.com.marcospaez.cursos.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_curso;
    private String nombre;
    private String descripcion;
    private String turno;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_profesor", nullable = false)
    private Profesor unProfesor;

    public Curso() {
    }

    public Curso(Long id_curso, String nombre, String descripcion, String turno, Profesor unProfesor) {
        this.id_curso = id_curso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.turno = turno;
        this.unProfesor = unProfesor;
    }
}
