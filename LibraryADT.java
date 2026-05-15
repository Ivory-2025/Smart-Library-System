import java.util.*;

public interface LibraryADT {

    /**
     * Adds a new book to the library catalogue (BST).
     * Duplicate ISBNs should be silently ignored or reported — M5 decides.
     *
     * @param isbn   Unique integer identifier for the book.
     * @param title  Title of the book.
     * @param author Author of the book.
     */
    void addBook(int isbn, String title, String author);

    /**
     * Searches the catalogue for a book by its ISBN.
     * Expected time complexity: O(log n) via BST traversal.
     * Prints the book details if found, or a "Not Found" message otherwise.
     *
     * @param isbn The ISBN to search for.
     */
    void searchBook(int isbn);

    /**
     * Borrows a book from the catalogue:
     *   1. Locates the book in the BST by ISBN.
     *   2. Pushes it onto the borrowing-history Stack (LIFO).
     * Prints a confirmation, or an error if the ISBN does not exist.
     *
     * @param isbn The ISBN of the book to borrow.
     */
    void borrowBook(int isbn);

    /**
     * Displays the full borrowing history in LIFO order
     * (most recently borrowed book appears first).
     * Prints "History is empty." when no books have been borrowed.
     */
    void viewLatestHistory();
}
