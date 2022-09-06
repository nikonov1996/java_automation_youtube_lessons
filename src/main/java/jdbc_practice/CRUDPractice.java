package jdbc_practice;

import jdbc_practice.db.DBUtils;
import jdbc_practice.models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUDPractice {

    private static String INSERT_STUDENT = "INSERT INTO student(name, university, age, course_count) values( ?,?,?,? );";
    private static String SELECT_ALL_STUDENTS = "SELECT * FROM student;";
    private static String SELECT_STUDENT_BY_ID = "SELECT * FROM student WHERE id= ?;";
    private static String UPDATE_STUDENT_BY_ID = "UPDATE student SET name= ? , university= ?, age= ? , course_count= ? WHERE id= ?";
    private static String DELETE_STUDENT_BY_ID = "DELETE FROM student WHERE id= ?";

    public static List<Student> getStudentData() {
        List<Student> students = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                students.add(new Student()
                        .withId(resultSet.getInt("id"))
                        .withName(resultSet.getString("name"))
                        .withUniversity(resultSet.getString("university"))
                        .withAge(resultSet.getInt("age"))
                        .withCourseCount(resultSet.getInt("course_count")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public static void saveStudent(Student student) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT);
        ) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getUniversity());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setInt(4, student.getCourseCount());
            preparedStatement.executeUpdate();
// Код чтобы вывести результат добавления записи в бд:
//            List<Student> students = new ArrayList<>();
//            PreparedStatement all = connection.prepareStatement(SELECT_ALL_STUDENTS);
//            ResultSet resultSet = all.executeQuery();
//            while (resultSet.next()) {
//                students.add(new Student()
//                        .withId(resultSet.getInt("id"))
//                        .withName(resultSet.getString("name"))
//                        .withUniversity(resultSet.getString("university"))
//                        .withAge(resultSet.getInt("age"))
//                        .withCourseCount(resultSet.getInt("course_count")));
//            }
//            return students;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Student> updateStudentById(Student student) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement old_student = connection.prepareStatement(SELECT_STUDENT_BY_ID);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT_BY_ID)
        ) {
            old_student.setInt(1, student.getId());
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getUniversity());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setInt(4, student.getCourseCount());
            preparedStatement.setInt(5, student.getId());

            if (old_student.executeQuery() != null) {
                preparedStatement.executeUpdate();
            }

// Код чтобы вывести результат добавления записи в бд:
            List<Student> students = new ArrayList<>();
            PreparedStatement all = connection.prepareStatement(SELECT_ALL_STUDENTS);
            ResultSet result = all.executeQuery();
            while (result.next()) {
                students.add(new Student()
                        .withId(result.getInt("id"))
                        .withName(result.getString("name"))
                        .withUniversity(result.getString("university"))
                        .withAge(result.getInt("age"))
                        .withCourseCount(result.getInt("course_count")));
            }
            return students;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Student> deleteStudentById(Student student) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement old_student = connection.prepareStatement(SELECT_STUDENT_BY_ID);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT_BY_ID)
        ) {
            old_student.setInt(1, student.getId());
            preparedStatement.setInt(1, student.getId());

            if (old_student.executeQuery() != null) {
                preparedStatement.executeUpdate();
            }

// Код чтобы вывести результат добавления записи в бд:
            List<Student> students = new ArrayList<>();
            PreparedStatement all = connection.prepareStatement(SELECT_ALL_STUDENTS);
            ResultSet result = all.executeQuery();
            while (result.next()) {
                students.add(new Student()
                        .withId(result.getInt("id"))
                        .withName(result.getString("name"))
                        .withUniversity(result.getString("university"))
                        .withAge(result.getInt("age"))
                        .withCourseCount(result.getInt("course_count")));
            }
            return students;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
