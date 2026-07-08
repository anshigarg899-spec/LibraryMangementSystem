import java.io.Serializable;

public class Book implements Serializable {

    private String id;
    private String title;
    private String author;
    private int quantity;    // total copies
    private int borrowed;    // issued copies

    public Book(String id, String title, String author, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.borrowed = 0;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getQuantity() { return quantity; }
    public int getBorrowed() { return borrowed; }

    // available copies = quantity - borrowed
    public boolean isAvailable() {
        return borrowed < quantity;
    }

    // borrow 1 copy
    public void borrowCopy() {
        if (borrowed < quantity) {
            borrowed++;
        }
    }

    // return 1 copy
    public void returnCopy() {
        if (borrowed > 0) {
            borrowed--;
        }
    }

    @Override
    public String toString() {
        return id + "," + title + "," + author + "," + quantity + "," + borrowed;
    }

    public static Book fromString(String data) {
        String[] arr = data.split(",");
        Book b = new Book(arr[0], arr[1], arr[2], Integer.parseInt(arr[3]));
        b.borrowed = Integer.parseInt(arr[4]);
        return b;
    }
}
