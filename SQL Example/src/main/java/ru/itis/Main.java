package ru.itis;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.models.Course;
import ru.itis.models.Student;
import ru.itis.repositories.*;

import java.sql.Date;


public class Main {
    public static void main(String[] args) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:54321/taxi_db");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("Hermine2023");
        hikariConfig.setDriverClassName("org.postgresql.Driver");

        HikariDataSource dataSource1 = new HikariDataSource(hikariConfig);
        System.out.println("Students list []");
        StudentsRepository studentsRepository = new StudentsRepositoryJdbcImpl(dataSource1);

        Student student = Student.builder()
                .firstName("Dome")
                .lastName("Tuqueres")
                .age(21)
                .build();

        studentsRepository.save(student);
        System.out.println(student);


        ///==============================================================
        StudentsRepository studentsRepositorySpringJdbc = new StudentsRepositorySpringJdbcImpl(dataSource1);

        Student student1 = Student.builder()
                .firstName("Camila")
                .lastName("Altamirano")
                .age(34)
                .build();

        System.out.println(student1);
        studentsRepository.save(student1);
        System.out.println(student1);

        System.out.println(studentsRepository.findAll());
        System.out.println();

///==============================================================


        System.out.println("Courses list []");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        CourseRepository coursesRepository = new CoursesRepositoryJdbcImpl(dataSource);

        Course course = Course.builder()
                .title("C++")
                .startDate(new Date(2012, 12, 8))
                .finishDate(new Date(2023, 2, 9))
                .build();

        coursesRepository.save(course);
        System.out.println(course);

///==============================================================
        CourseRepository coursesRepositorySpringJdbc = new CoursesRepositorySpringJdbcImpl(dataSource1);

        Course course1 = Course.builder()
                .title("Phyton")
                .startDate(new Date(2011, 12, 8))
                .finishDate(new Date(2013, 2, 9))
                .build();

        System.out.println(course1);
        coursesRepository.save(course1);
        System.out.println(course1);

        System.out.println(studentsRepository.findAll());
        System.out.println();

    }
}