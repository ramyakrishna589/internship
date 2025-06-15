import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

abstract class Member {
    UUID memberID;
    String name, email, phone;
    int maxBooksAllowed;
    List<Book> currentlyIssuedBooks = new ArrayList<>();

    Member(String name, String email, String phone) {
        this.memberID = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    abstract int getMaxAllowedDays();
    abstract String getMemberType();
}

class StudentMember extends Member {
    StudentMember(String name, String email, String phone) {
        super(name, email, phone);
        this.maxBooksAllowed = 3;
    }

    int getMaxAllowedDays() { return 14; }
    String getMemberType() { return "Student"; }
}

class TeacherMember extends Member {
    TeacherMember(String name, String email, String phone) {
        super(name, email, phone);
        this.maxBooksAllowed = 5;
    }

    int getMaxAllowedDays() { return 30; }
    String getMemberType() { return "Teacher"; }
}

class GuestMember extends Member {
    GuestMember(String name, String email, String phone) {
        super(name, email, phone);
        this.maxBooksAllowed = 1;
    }

    int getMaxAllowedDays() { return 7; }
    String getMemberType() { return "Guest"; }
}

class Librarian extends Member {
    Librarian(String name, String email, String phone) {
        super(name, email, phone);
        this.maxBooksAllowed = Integer.MAX_VALUE;
    }

    int getMaxAllowedDays() { return Integer.MAX_VALUE; }
    String getMemberType() { return "Librarian"; }
}

class Book {
    UUID bookID;
    String title, author, genre;
    boolean isIssued;
    Member issuedTo;
    LocalDate dueDate;
    Queue<Member> reservationQueue = new LinkedList<>();

    Book(String title, String author, String genre) {
        this.bookID = UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isIssued = false;
    }

    @Override
    public String toString() {
        return String.format("%s by %s [%s] %s",
            title, author, genre, (isIssued ? "(Issued)" : "(Available)"));
    }
}

class Library {
    Map<UUID, Book> catalog = new HashMap<>();
    Map<String, Member> membersByContact = new HashMap<>();

    void addBook(Book b) {
        catalog.put(b.bookID, b);
        System.out.println("Book added: " + b);
        System.out.println("Book ID: " + b.bookID);
    }

    void removeBook(UUID bookId) {
        Book b = catalog.get(bookId);
        if (b == null) {
            System.out.println("Book not found");
        } else if (b.isIssued) {
            System.out.println("Cannot remove an issued book");
        } else {
            catalog.remove(bookId);
            System.out.println("Removed book: " + b);
        }
    }

    void registerMember(Member m) {
        if (membersByContact.containsKey(m.email) || membersByContact.containsKey(m.phone)) {
            System.out.println("Member already registered");
        } else {
            membersByContact.put(m.email, m);
            membersByContact.put(m.phone, m);
            System.out.printf("%s registered: %s (%s)\n",
                m.getMemberType(), m.name, m.email);
        }
    }

    void issueBook(UUID bookId, String contact) {
        Book b = catalog.get(bookId);
        Member m = membersByContact.get(contact);
        if (b == null || m == null) {
            System.out.println("Book or member not found");
            return;
        }
        if (b.isIssued) {
            System.out.println("Book already issued");
        } else if (m.currentlyIssuedBooks.size() >= m.maxBooksAllowed) {
            System.out.println("Member reached limit");
        } else {
            b.isIssued = true;
            b.issuedTo = m;
            b.dueDate = LocalDate.now().plusDays(m.getMaxAllowedDays());
            m.currentlyIssuedBooks.add(b);
            System.out.printf("Issued \"%s\" to %s; due %s\n",
                b.title, m.name, b.dueDate);
        }
    }

    void returnBook(UUID bookId, String contact) {
        Book b = catalog.get(bookId);
        Member m = membersByContact.get(contact);
        if (b == null || m == null ||
            !b.isIssued || !b.issuedTo.equals(m)) {
            System.out.println("Return not valid");
            return;
        }
        m.currentlyIssuedBooks.remove(b);
        b.isIssued = false;
        b.issuedTo = null;
        b.dueDate = null;
        System.out.println("Book returned: " + b.title);

        if (!b.reservationQueue.isEmpty()) {
            Member next = b.reservationQueue.poll();
            issueBook(bookId, next.email); // or next.phone
        }
    }

    void reserveBook(UUID bookId, String contact) {
        Book b = catalog.get(bookId);
        Member m = membersByContact.get(contact);
        if (b == null || m == null) {
            System.out.println("Book or member not found");
            return;
        }
        if (!b.isIssued) {
            System.out.println("Book is available; no need to reserve.");
        } else {
            b.reservationQueue.add(m);
            System.out.printf("%s reserved \"%s\"\n", m.name, b.title);
        }
    }

    void searchBooks(String kw) {
        catalog.values().stream()
            .filter(b -> b.title.contains(kw) ||
                         b.author.contains(kw) ||
                         b.genre.contains(kw))
            .forEach(System.out::println);
    }

    void viewIssuedBooks(String contact) {
        Member m = membersByContact.get(contact);
        if (m == null) {
            System.out.println("Member not found");
            return;
        }
        if (m.currentlyIssuedBooks.isEmpty()) {
            System.out.println("No books issued");
            return;
        }
        for (Book b : m.currentlyIssuedBooks) {
            long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), b.dueDate);
            System.out.printf("\"%s\" due in %d day(s)\n", b.title, daysLeft);
        }
    }

    void viewOverdues() {
        catalog.values().stream()
            .filter(b -> b.isIssued && b.dueDate.isBefore(LocalDate.now()))
            .forEach(b -> System.out.printf("OVERDUE: \"%s\" (issued to %s)\n",
                b.title, b.issuedTo.name));
    }
}

public class Task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();
        // Pre-register a librarian
        lib.registerMember(new Librarian("LibAdmin","admin@lib.com","000"));

        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book  2. Register Member");
            System.out.println("3. Issue Book  4. Return Book");
            System.out.println("5. Reserve Book  6. Search Books");
            System.out.println("7. View Issued  8. View Overdues");
            System.out.println("9. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Title: ");
                    String t = sc.nextLine();
                    System.out.print("Author: ");
                    String a = sc.nextLine();
                    System.out.print("Genre: ");
                    String g = sc.nextLine();
                    lib.addBook(new Book(t,a,g));
                }
                case 2 -> {
                    System.out.print("Type (student/teacher/guest): ");
                    String type = sc.nextLine().toLowerCase();
                    System.out.print("Name: ");
                    String n = sc.nextLine();
                    System.out.print("Email: ");
                    String e = sc.nextLine();
                    System.out.print("Phone: ");
                    String p = sc.nextLine();
                    Member m = switch (type) {
                        case "student" -> new StudentMember(n,e,p);
                        case "teacher" -> new TeacherMember(n,e,p);
                        case "guest"   -> new GuestMember(n,e,p);
                        default -> {
                            System.out.println("Invalid type"); yield null;
                        }
                    };
                    if (m != null) lib.registerMember(m);
                }
                case 3, 4, 5 -> {
                    System.out.print("Book ID: ");
                    try {
                        UUID bid = UUID.fromString(sc.nextLine().trim());
                        System.out.print("Your email or phone: ");
                        String contact = sc.nextLine().trim();
                        if (choice == 3) lib.issueBook(bid, contact);
                        else if (choice == 4) lib.returnBook(bid, contact);
                        else lib.reserveBook(bid, contact);
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Wrong Book ID format");
                    }
                }
                case 6 -> {
                    System.out.print("Keyword: ");
                    String kw = sc.nextLine();
                    lib.searchBooks(kw);
                }
                case 7 -> {
                    System.out.print("Your contact (email/phone): ");
                    String contact = sc.nextLine();
                    lib.viewIssuedBooks(contact);
                }
                case 8 -> lib.viewOverdues();
                case 9 -> {
                    System.out.println("Exiting…");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }
}
