import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Library {

    private List<Book> books = new ArrayList<>();

    public Library() {
        loadBooks();
    }

    // ---------------------- Load & Save Books ----------------------

    private void loadBooks() {
        try (BufferedReader br = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                books.add(Book.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("No books.txt found. Starting with empty library.");
        }
    }

    private void saveBooks() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("books.txt"))) {
            for (Book b : books) {
                pw.println(b.toString());
            }
        } catch (IOException e) {
            System.out.println("Error saving books!");
        }
    }

    // ---------------------- Admin Features ----------------------

    public void addBook(Book book) {
        books.add(book);
        saveBooks();
        System.out.println("Book added successfully!");
    }

    public void showBooks() {
        System.out.println("\nAvailable Books:");
        for (Book b : books) {
            System.out.println(
                b.getId() + " - " + b.getTitle() +
                " | Total: " + b.getQuantity() +
                " | Borrowed: " + b.getBorrowed() +
                " | Available: " + (b.getQuantity() - b.getBorrowed())
            );
        }
    }

    // ---------------------- Borrow Book ----------------------

    public void borrowBook(String bookId, User user) {

        for (Book b : books) {
            if (b.getId().equals(bookId)) {

                if (!b.isAvailable()) {
                    System.out.println("No copies available (OUT OF STOCK).");
                    return;
                }

                b.borrowCopy();
                saveBooks();

                try (PrintWriter pw = new PrintWriter(new FileWriter("transactions.txt", true))) {
                    pw.println(user.getId() + "," + bookId + "," + LocalDate.now());
                } catch (IOException e) {
                    System.out.println("Error writing transaction file!");
                }

                System.out.println("\nBook issued to: " + user.getName());
                System.out.println("Remaining copies: " + (b.getQuantity() - b.getBorrowed()));
                return;
            }
        }

        System.out.println("Book not found!");
    }

    // ---------------------- Return Book ----------------------

    public void returnBook(String bookId, User user) {

        for (Book b : books) {

            if (b.getId().equals(bookId)) {

                if (b.getBorrowed() == 0) {
                    System.out.println("This book is NOT borrowed by anyone!");
                    return;
                }

                LocalDate borrowDate = null;

                try (BufferedReader br = new BufferedReader(new FileReader("transactions.txt"))) {
                    String line;

                    while ((line = br.readLine()) != null) {
                        String[] arr = line.split(",");

                        if (arr[0].equals(user.getId()) && arr[1].equals(bookId)) {
                            borrowDate = LocalDate.parse(arr[2]);
                            break;
                        }
                    }

                } catch (IOException e) {
                    System.out.println("Error reading transactions!");
                }

                if (borrowDate == null) {
                    System.out.println("No borrow record found for this user!");
                    return;
                }

                LocalDate returnDate = LocalDate.now();
                int fine = FineCalculator.calculateFine(user.getUserType(), borrowDate, returnDate);

                System.out.println("\nBook returned successfully!");
                System.out.println("Fine to pay: ₹" + fine);

                b.returnCopy();
                saveBooks();

                System.out.println("Available copies: " + (b.getQuantity() - b.getBorrowed()));
                return;
            }
        }

        System.out.println("Book not found!");
    }
}
