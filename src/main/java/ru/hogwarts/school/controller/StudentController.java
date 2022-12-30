package ru.hogwarts.school.controller;

import ru.hogwarts.school.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;


    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
    }

    @GetMapping("/age/between")
    public Collection<Student> findStudentsByAge(@RequestParam("minAge") int min,
                                                 @RequestParam("maxAge") int max) {
        return this.studentService.findStudentsByAge(min, max);
    }


    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/students/getAll")
    public Integer getAllByName() {
        return studentService.getAllByName();
    }

    @GetMapping("/student/findByAge")
    public Integer findByAge() {
        return studentService.findByAge();
    }

    @GetMapping("/student/getStudentById")
    public Set<Student> getStudentById() {
        return studentService.getStudentsById();
    }

    @GetMapping("/student/findAllName/")
    ResponseEntity<String> findAllNameStudents() {
        return studentService.findAllNameStudents();
    }

    @GetMapping("/student/getAverageAge/")
    ResponseEntity<Double> findAverageAgeStudents() {
        return studentService.findAverageAgeStudents();
    }

    @GetMapping("/returnInteger")
    Integer returnInteger() {
        int sum = Stream.iterate(1, a -> a + 1).limit(1_000_000)
                .parallel().reduce(0, (a, b) -> a + b);
        return sum;
    }


}
