import java.io.*;
import java.util.*;

public class StudentManager {
    private ArrayList<Student> students = new ArrayList<>();
    private HashMap<Integer, Student> studentMap = new HashMap<>();
    private TreeSet<Student> studentSet = new TreeSet<>();

    public void addStudent(Student student) {
        if (studentMap.containsKey(student.getId())) {
            System.out.println("❌ ID already exists!");
            return;
        }
        students.add(student);
        studentMap.put(student.getId(), student);
        studentSet.add(student);
        System.out.println("✅ Student added successfully!");
    }

    public void removeStudent(int id) {
        Student s = studentMap.get(id);
        if (s == null) {
            System.out.println("❌ Student not found.");
            return;
        }
        students.remove(s);
        studentMap.remove(id);
        studentSet.remove(s);
        System.out.println("✅ Student removed.");
    }

    public void updateStudent(int id, String name, int age, String grade, String address) {
        Student s = studentMap.get(id);
        if (s == null) {
            System.out.println("❌ Student not found.");
            return;
        }
        s.setName(name);
        s.setAge(age);
        s.setGrade(grade);
        s.setAddress(address);
        System.out.println("✅ Student updated.");
    }

    public Student searchStudent(int id) {
        return studentMap.get(id);
    }

    public void displayAllStudents() {
        if (studentSet.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        for (Student s : studentSet) {
            System.out.println(s);
        }
    }

    public void loadFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            students = (ArrayList<Student>) in.readObject();
            studentMap.clear();
            studentSet.clear();
            for (Student s : students) {
                studentMap.put(s.getId(), s);
                studentSet.add(s);
            }
            System.out.println(" Data loaded.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(" Could not load file. Starting fresh.");
        }
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(students);
            System.out.println(" Data saved to file.");
        } catch (IOException e) {
            System.out.println(" Error saving file: " + e.getMessage());
        }
    }
}
