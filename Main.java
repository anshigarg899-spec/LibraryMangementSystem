import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Library lib = new Library();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n====== LIBRARY MANAGEMENT SYSTEM ======");
            System.out.println("1. Admin");
            System.out.println("2. Student");
            System.out.println("3. Teacher");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int ch = sc.nextInt();

            switch (ch) {

                // ------------------- ADMIN MENU -------------------
                case 1:
                    adminMenu(lib, sc);
                    break;

                // ------------------- STUDENT LOGIN -------------------
                case 2:
                    System.out.print("Enter Student ID: ");
                    String sid = sc.next();

                    System.out.print("Enter Student Name: ");
                    String sname = sc.next();

                    User student = new Student(sid, sname, "");
                    userMenu(lib, sc, student);
                    break;

                // ------------------- TEACHER LOGIN -------------------
                case 3:
                    System.out.print("Enter Teacher ID: ");
                    String tid = sc.next();

                    System.out.print("Enter Teacher Name: ");
                    String tname = sc.next();

                    User teacher = new Teacher(tid, tname, "");
                    userMenu(lib, sc, teacher);
                    break;

                case 4:
                    System.out.println("Exiting system...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ------------------- USER MENU -------------------

    public static void userMenu(Library lib, Scanner sc, User user) {

        System.out.println("\nWelcome " + user.getName() + " (" + user.getUserType() + ")");
        System.out.println("1. Borrow Book");
        System.out.println("2. Return Book");
        System.out.print("Choose: ");
        int c = sc.nextInt();

        System.out.print("Enter Book ID: ");
        String bookId = sc.next();

        if (c == 1) {
            lib.borrowBook(bookId, user);
        } else if (c == 2) {
            lib.returnBook(bookId, user);
        } else {
            System.out.println("Invalid option!");
        }
    }

    // ------------------- ADMIN MENU -------------------

    public static void adminMenu(Library lib, Scanner sc) {
        System.out.println("\n====== ADMIN PANEL ======");
        System.out.println("1. Add Book");
        System.out.println("2. Show Books");
        System.out.print("Choose: ");
        int c = sc.nextInt();

        if (c == 1) {
            System.out.print("Enter Book ID: ");
            String id = sc.next();

            System.out.print("Enter Title: ");
            String title = sc.next();

            System.out.print("Enter Author: ");
            String author = sc.next();

            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();

            lib.addBook(new Book(id, title, author, qty));
        }
        else if (c == 2) {
            lib.showBooks();
        }
        else {
            System.out.println("Invalid option!");
        }
    }
}
