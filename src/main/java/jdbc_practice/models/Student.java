package jdbc_practice.models;

public class Student {

    private int id;
    private String name;
    private String university;
    private int age;
    private int courseCount;


    public Integer getId() {
        return id;
    }

    public Student withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Student withName(String name) {
        this.name = name;
        return this;
    }

    public String getUniversity() {
        return university;
    }

    public Student withUniversity(String university) {
        this.university = university;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Student withAge(int age) {
        this.age = age;
        return this;
    }

    public int getCourseCount() {
        return courseCount;
    }

    public Student withCourseCount(int course_count) {
        this.courseCount = course_count;
        return this;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", university='" + university + '\'' +
                ", age=" + age +
                ", courseCount=" + courseCount +
                '}';
    }
}
