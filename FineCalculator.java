import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FineCalculator {

    public static int calculateFine(String userType, LocalDate borrowDate, LocalDate returnDate) {

        // due date = 15 days after borrow
        LocalDate dueDate = borrowDate.plusDays(15);

        // no fine if returned on/before due date
        if (!returnDate.isAfter(dueDate)) {
            return 0;
        }

        long lateDays = ChronoUnit.DAYS.between(dueDate, returnDate);

        int rate = 0;

        if (userType.equalsIgnoreCase("student")) {
            rate = 20; // ₹20/day
        } 
        else if (userType.equalsIgnoreCase("teacher")) {
            rate = 15; // ₹15/day
        }

        return (int)(lateDays * rate);
    }
}
