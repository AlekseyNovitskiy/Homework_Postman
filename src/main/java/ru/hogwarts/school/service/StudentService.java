package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Student;
import org.springframework.stereotype.Service;

import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class StudentService {

    public static final Object flag = new Object();

    private final StudentRepository studentRepository;

    private static  final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student addStudent(Student student) {
        logger.info("Был вызван метод addStudent()");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Был вызван метод findStudent()");
        return this.studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        logger.info("Был вызван метод editStudent()");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Был вызван метод deleteStudent()");
        studentRepository.deleteById(id);;
    }

    public Collection<Student> getAllStudents() {
        logger.info("Был вызван метод getAllStudents()");
        return studentRepository.findAll();
    }

    public Collection<Student> getByAge(int age) {
        logger.info("Был вызван метод getByAge()");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findStudentsByAge(int minAge, int maxAge) {
        logger.info("Был вызван метод findStudentsByAge()");
        return this.studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public Integer getAllByName() {
        logger.info("Был вызван метод getAllByName()");
        return studentRepository.getAllByName();
    }

    public Integer findByAge() {
        logger.info("Был вызван метод findByAge()");
        return studentRepository.findByAge();
    }

    public Set<Student> getStudentsById(){
    logger.info("Был вызван метод getStudentsById()");
        return studentRepository.findStudentsById();
    }
    public ResponseEntity<String> findAllNameStudents(){
        String studentsName = studentRepository.findAll().stream()
                .map(Student::getName).map(String::toUpperCase)
                .filter(name -> (name.charAt(0)=='A'))
                .sorted().collect(Collectors.joining(", "));
        return ResponseEntity.ok(studentsName);
    }
    public ResponseEntity<Double> findAverageAgeStudents(){
        double averageAge= studentRepository.findAll().stream()
                .mapToInt(Student::getAge).summaryStatistics().getAverage();
        return ResponseEntity.ok(averageAge);
    }

    public List<Student> getAllStudentsAndName() {
        List<Student> students = studentRepository.findAll();
        students.stream().map(Student::getName).limit(2).forEach(System.out::println);
        Thread thread1 = new Thread(() -> {
            students.stream().map(Student::getName).skip(2).limit(1).forEach(System.out::println);
            students.stream().map(Student::getName).skip(3).limit(1).forEach(System.out::println);
        });
        Thread thread2 = new Thread(() -> {
            students.stream().map(Student::getName).skip(4).limit(1).forEach(System.out::println);
            students.stream().map(Student::getName).skip(5).limit(1).forEach(System.out::println);
        });
        thread1.start();
        thread2.start();
        return students;
    }

    public List<Student> getAllStudentsAndNameSynchronized(){
        List<Student> students2 = studentRepository.findAll();
        methodSynchronized(students2.stream().map(Student::getName).limit(1)
                .collect(Collectors.joining()));
        methodSynchronized(students2.stream().map(Student::getName).skip(1).limit(1)
                .collect(Collectors.joining(",")));
        Thread thread1 = new Thread(() -> {
            methodSynchronized(students2.stream().map(Student::getName).skip(2).limit(1)
                    .collect(Collectors.joining(",")));
            methodSynchronized(students2.stream().map(Student::getName).skip(3).limit(1)
                    .collect(Collectors.joining(",")));
        });
        Thread thread2 = new Thread(() -> {
            methodSynchronized(students2.stream().map(Student::getName).skip(4).limit(1)
                    .collect(Collectors.joining(",")));
            methodSynchronized(students2.stream().map(Student::getName).skip(5).limit(1)
                    .collect(Collectors.joining(",")));
        });
        thread1.start();
        thread2.start();
        return students2;
    }

    public static void methodSynchronized(String name){
        synchronized (flag){
            System.out.println(name);
        }
    }


}
