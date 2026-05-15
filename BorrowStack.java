import java.util.Stack;

public class BorrowStack {
    private Stack<Book> borrowHistory;

    public BorrowStack() {
        this.borrowHistory = new Stack<>();
    }

    public void pushBorrowRecord(Book book) {
        if (book != null) {
            borrowHistory.push(book);
            System.out.println("Borrow record added: " + book.toString());
        } else {
            System.out.println("Failed: Invalid book object");
        }
    }

    public void displayLIFOHistory() {
        if (borrowHistory.isEmpty()) {
            System.out.println("No borrowing records available.");
            return;
        }

        System.out.println("\n Borrowing History (Latest First - LIFO):");
        System.out.println("==================================================");

        for (int i = borrowHistory.size() - 1; i >= 0; i--) {
            Book currentBook = borrowHistory.get(i);
            System.out.println("• " + currentBook.toString());
            System.out.println("--------------------------------------------------");
        }
    }

    public int getBorrowCount() {
        return borrowHistory.size();
    }

    public boolean isHistoryEmpty() {
        return borrowHistory.isEmpty();
    }

    public Book getLatestBorrow() {
        if (!borrowHistory.isEmpty()) {
            return borrowHistory.peek();
        }
        return null;
    }
}