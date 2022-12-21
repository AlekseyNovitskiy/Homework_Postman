package ru.hogwarts.school.service;

import ru.hogwarts.school.exception.ObjectNotFoundException;
import ru.hogwarts.school.model.Faculty;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.repository.FacultyRepository;


import java.util.Collection;
import java.util.Set;


@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }
    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).get();
    }
    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }
    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }
    public Collection<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Faculty findByNameOrColor(String nameOrColor){
        return this.facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(nameOrColor, nameOrColor)
                .orElseThrow(ObjectNotFoundException::new);
    }

    public Set<Faculty> findByColor(String color) {
        return facultyRepository.findByColor(color);
    }

}
