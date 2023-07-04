package ar.com.marcospaez.cursos.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_profesor;
    private String nombre;
    private String email;

    public Profesor() {
    }

    public Profesor(Long id_profesor, String nombre, String email) {
        this.id_profesor = id_profesor;
        this.nombre = nombre;
        this.email = email;
    }
}
