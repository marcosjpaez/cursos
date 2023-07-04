package ar.com.marcospaez.cursos.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter @Setter
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_alumno;
    private String nombre;
    private String dni;
    private String email;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaNac;

    public Alumno() {
    }

    public Alumno(Long id_alumno, String nombre, String dni, String email, LocalDate fechaNac) {
        this.id_alumno = id_alumno;
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.fechaNac = fechaNac;
    }
}
