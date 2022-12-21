package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;



import java.util.List;
import java.util.Set;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query(value = "SELECT COUNT(*) From student", nativeQuery = true)
    Integer getAllByName();

    @Query(value = "SELECT avg(age) From student", nativeQuery = true)
    Integer findByAge();

    @Query(value = "SELECT * From student order by id desc limit 5", nativeQuery = true)
    Set<Student> findStudentsById();

    List<Student> findByAge(int age);
    List<Student> findByAgeBetween(int minAge, int maxAge);


}
