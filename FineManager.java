import java.util.ArrayList;
 
public class FineManager {
 
    private class FineRecord {
        String studentName;
        Book book;
        int daysOverdue;
        double fineAmount;
 
        FineRecord(String studentName, Book book, int daysOverdue) {
            this.studentName = studentName;
            this.book        = book;
            this.daysOverdue = daysOverdue;
            this.fineAmount  = daysOverdue * 0.50;
        }
    }
 
    private static final double FINE_PER_DAY = 0.50;
    private ArrayList<FineRecord> fineList = new ArrayList<>();
 
    public void addFine(String studentName, Book book, int daysOverdue) {
        if (daysOverdue <= 0) {
            System.out.println("Days overdue must be at least 1.");
            return;
        }
        fineList.add(new FineRecord(studentName, book, daysOverdue));
        System.out.println("Fine recorded for " + studentName + ":");
        System.out.println("  Book    : " + book.toString());
        System.out.println("  Overdue : " + daysOverdue + " day(s)");
        System.out.println("  Fine    : RM " + String.format("%.2f", daysOverdue * FINE_PER_DAY));
    }
 
    public void displayAllFines() {
        if (fineList.isEmpty()) {
            System.out.println("No outstanding fines.");
            return;
        }
        System.out.println("\n--- Outstanding Fines ---");
        for (int i = 0; i < fineList.size(); i++) {
            FineRecord f = fineList.get(i);
            System.out.println((i + 1) + ". Student : " + f.studentName);
            System.out.println("   Book    : " + f.book.toString());
            System.out.println("   Overdue : " + f.daysOverdue + " day(s)");
            System.out.println("   Fine    : RM " + String.format("%.2f", f.fineAmount));
        }
    }
}
