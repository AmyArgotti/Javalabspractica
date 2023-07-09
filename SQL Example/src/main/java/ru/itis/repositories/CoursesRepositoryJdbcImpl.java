package ru.itis.repositories;

import ru.itis.models.Course;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursesRepositoryJdbcImpl implements CourseRepository {

    //language=sql
    private final static String SQL_SELECT_ALL = "select * from course";

    //language=sql
    private final static String SQL_INSERT = "insert into course(title, start_date, finish_date) values (?, ?, ?)";

    private final DataSource dataSource; // источник подключений к базе

    public CoursesRepositoryJdbcImpl(DataSource dataSource) {this.dataSource = dataSource;
    }
    @Override
    public void save(Course model) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, model.getTitle());
                statement.setDate(2,  model.getStartDate());
                statement.setDate(3,  model.getFinishDate());

                int affectedRows = statement.executeUpdate();

                if (affectedRows != 1) {
                    throw new SQLException("Cannot insert course");
                }

                try (ResultSet generatedIds = statement.getGeneratedKeys()){
                    if (generatedIds.next()) {
                        model.setId(generatedIds.getInt("id"));
                    } else {
                        throw new SQLException("Cannot retrieve id");
                    }
                }

            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }



    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)) {
                while (resultSet.next()) { // iterator
                    Course course = Course.builder()
                            .id(resultSet.getInt("id"))
                            .title(resultSet.getString("title"))
                            .startDate( resultSet.getDate("start_date"))
                            .finishDate( resultSet.getDate("finish_date"))
                            .build();

                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e); // если произошла ошибка с базой - просто остановим приложение
        }
        return courses;
    }
}
