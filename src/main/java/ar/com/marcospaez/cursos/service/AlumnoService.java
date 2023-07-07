package ar.com.marcospaez.cursos.service;

import ar.com.marcospaez.cursos.entity.Alumno;
import ar.com.marcospaez.cursos.exceptions.MiException;
import ar.com.marcospaez.cursos.repository.IAlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AlumnoService implements IAlumnoService {

    @Autowired
    private IAlumnoRepository alumnoRepository;
    @Override
    public List<Alumno> getAlumnos() {
        List<Alumno> listaAlumnos = alumnoRepository.findAll();
        return listaAlumnos;
    }

    @Override
    public void saveAlumno(Alumno alumno) throws MiException {
        if(alumno.getNombre().isEmpty()) {
            throw new MiException("el nombre no puede estar vacío");
        } else if (alumno.getEmail().isEmpty()) {
            throw new MiException("el email no puede estar vacío");
        } else if (alumno.getDni().isEmpty()) {
            throw new MiException("el DNI no puede estar vacío");
        } else if (alumno.getFechaNac()== null) {
            throw new MiException("la fecha tiene un formato incorrecto");
        }
        alumnoRepository.save(alumno);
    }

    @Override
    public void deleteAlumno(Long id_alumno) {
        alumnoRepository.deleteById(id_alumno);
    }

    @Override
    public Alumno findAlumno(Long id_alumno) {
        Alumno alumno = alumnoRepository.findById(id_alumno).orElse(null);
        return alumno;
    }

    @Override
    public void editAlumno(Long id_alumno, String nuevoNombre, String nuevoEmail, String nuevoDni, LocalDate nuevaFechaNac) throws MiException {
        Alumno alumno = this.findAlumno(id_alumno);
        alumno.setNombre(nuevoNombre);
        alumno.setEmail(nuevoEmail);
        alumno.setDni(nuevoDni);
        alumno.setFechaNac(nuevaFechaNac);
        this.saveAlumno(alumno);
    }
}
