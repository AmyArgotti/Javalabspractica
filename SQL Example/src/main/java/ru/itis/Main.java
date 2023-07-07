package ru.itis;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.models.Course;
import ru.itis.models.Student;
import ru.itis.repositories.CourseRepository;
import ru.itis.repositories.CoursesRepositoryJdbcImpl;
import ru.itis.repositories.StudentsRepository;
import ru.itis.repositories.StudentsRepositoryJdbcImpl;

public class Main {
    public static void main(String[] args) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:54321/taxi_db");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("Hermine2023");
        hikariConfig.setDriverClassName("org.postgresql.Driver");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        StudentsRepository studentsRepository = new StudentsRepositoryJdbcImpl(dataSource);

        Student student = Student.builder()
                .firstName("Dome")
                .lastName("Tuqueres")
                .age(21)
                .build();

//        System.out.println(student);
        studentsRepository.save(student);
        System.out.println(student);
        System.out.println(studentsRepository.findAll());
///==============================================================



        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        CourseRepository coursesRepository = new CoursesRepositoryJdbcImpl(dataSource);

        Course course = Course.builder()
                .title("C++")
               .startDate("08.12.2012")
               .finishDate("09.02.2023")
                .build();

//        System.out.println(student);
        coursesRepository.save(course);
        System.out.println(course);

        System.out.println(coursesRepository.findAll());
    }
}