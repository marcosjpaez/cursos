package ar.com.marcospaez.cursos.service;

import ar.com.marcospaez.cursos.entity.Profesor;
import ar.com.marcospaez.cursos.repository.IProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService  implements IProfesorService{

    @Autowired
    private IProfesorRepository profesorRepository;
    @Override
    public List<Profesor> getProfesores() {
        List<Profesor> listaProfesores = profesorRepository.findAll();
        return listaProfesores;
    }
    @Override
    public void saveProfesor(Profesor profesor) {
        profesorRepository.save(profesor);
    }
    @Override
    public void deleteProfesor(Long id_profesor) {
        profesorRepository.deleteById(id_profesor);
    }
    @Override
    public Profesor findProfesor(Long id_profesor) {
        Profesor profesor = profesorRepository.findById(id_profesor).orElse(null);
        return profesor;
    }

    @Override
    public void editProfesor(Long id_profesor, String nuevoNombre, String nuevoEmail) {
        Profesor profesor = this.findProfesor(id_profesor);
        profesor.setNombre(nuevoNombre);
        profesor.setEmail(nuevoEmail);
        this.saveProfesor(profesor);
    }
}
