package jdbc_practice;

import jdbc_practice.models.Student;

import java.util.List;

public class app {
    public static void main(String[] args) {

        Student student1 = new Student()
                .withId(9) //
                .withName("Фёдор Тимофеевич") // Князев Фёдор Тимофеевич
                .withUniversity("Томский государственный университет") // Национальный исследовательский Томский государственный университет
                .withAge(20) //26
                .withCourseCount(10); //5


        List<Student> students2 = CRUDPractice.deleteStudentById(student1);
        students2.forEach(System.out::println);

    }
}
