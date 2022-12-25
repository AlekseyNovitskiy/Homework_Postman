package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hogwarts.school.exception.ObjectNotFoundException;
import ru.hogwarts.school.model.Faculty;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.repository.FacultyRepository;


import java.util.Collection;
import java.util.Set;


@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    private static  final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
    public Faculty addFaculty(Faculty faculty) {
        logger.info("Был вызван метод addFaculty()");
        return facultyRepository.save(faculty);
    }
    public Faculty findFaculty(long id) {
        logger.info("Был вызван метод findFaculty()");
        return facultyRepository.findById(id).get();
    }
    public Faculty editFaculty(Faculty faculty) {
        logger.info("Был вызван метод editFaculty()");
        return facultyRepository.save(faculty);
    }
    public void deleteFaculty(long id) {
        logger.info("Был вызван метод deleteFaculty()");
        facultyRepository.deleteById(id);
    }
    public Collection<Faculty> getAllFaculties() {
        logger.info("Был вызван метод getAllFaculties()");
        return facultyRepository.findAll();
    }

    public Faculty findByNameOrColor(String nameOrColor){
        logger.info("Был вызван метод findByNameOrColor()");
        return this.facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(nameOrColor, nameOrColor)
                .orElseThrow(ObjectNotFoundException::new);
    }

    public Set<Faculty> findByColor(String color) {
        logger.info("Был вызван метод findByColor()");
        return facultyRepository.findByColor(color);
    }

}
