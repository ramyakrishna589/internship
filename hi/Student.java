import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable, Comparable<Student> {
    private int id;
    private String name;
    private int age;
    private String grade;
    private String address;

    public Student(int id, String name, int age, String grade, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.address = address;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGrade() { return grade; }
    public String getAddress() { return address; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setGrade(String grade) { this.grade = grade; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return "Student{" +
               "ID=" + id +
               ", Name='" + name + '\'' +
               ", Age=" + age +
               ", Grade='" + grade + '\'' +
               ", Address='" + address + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Student s) {
        return Integer.compare(this.id, s.id); // Sort by ID by default
    }
}
