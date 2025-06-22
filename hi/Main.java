import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        String filename = "students.dat";

        manager.loadFromFile(filename);

        while (true) {
            System.out.println("\n Student Management System ");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Update Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Grade: ");
                    String grade = sc.nextLine();
                    System.out.print("Enter Address: ");
                    String address = sc.nextLine();

                    if (age <= 0 || name.isEmpty() || address.isEmpty()) {
                        System.out.println(" Invalid input.");
                        break;
                    }

                    Student s = new Student(id, name, age, grade, address);
                    manager.addStudent(s);
                }
                case 2 -> {
                    System.out.print("Enter ID to remove: ");
                    int id = sc.nextInt();
                    manager.removeStudent(id);
                }
                case 3 -> {
                    System.out.print("Enter ID to update: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter New Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Grade: ");
                    String grade = sc.nextLine();
                    System.out.print("Enter New Address: ");
                    String address = sc.nextLine();
                    manager.updateStudent(id, name, age, grade, address);
                }
                case 4 -> {
                    System.out.print("Enter ID to search: ");
                    int id = sc.nextInt();
                    Student s = manager.searchStudent(id);
                    if (s == null) System.out.println(" Not found.");
                    else System.out.println(s);
                }
                case 5 -> manager.displayAllStudents();
                case 6 -> {
                    manager.saveToFile(filename);
                    System.out.println(" Exiting...");
                    return;
                }
                default -> System.out.println(" Invalid choice.");
            }
        }
    }
}
