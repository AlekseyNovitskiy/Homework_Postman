package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public Collection<Faculty> findByColor(String color) {
        return facultyRepository.findAll().stream().filter(s-> Objects.equals(s.getColor(), color)).collect(Collectors.toList());
    }
    public Faculty findFacultyByColorIgnoreCaseOrNameIgnoreCase(String color, String name){
        return facultyRepository.findFacultyByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

}
