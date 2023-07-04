package ar.com.marcospaez.cursos.service;

import ar.com.marcospaez.cursos.entity.Profesor;

import java.util.List;

public interface IProfesorService {

    public List<Profesor> getProfesores();

    public void saveProfesor(Profesor profesor);

    public void deleteProfesor(Long id_profesor);

    public Profesor findProfesor(Long id_profesor);

    public void editProfesor(Long id_profesor, String nuevoNombre, String nuevoEmail);
}
