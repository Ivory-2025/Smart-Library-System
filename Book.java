public class Book {
    int isbn;    // Unique identifier; used as the BST key
    String title;
    String author;

    // ── BST node pointers (used by BookBST only) ──────────────
    Book left;
    Book right;

    public Book(int isbn, String title, String author) {
        this.isbn   = isbn;
        this.title  = title;
        this.author = author;
        this.left   = null;   // explicitly null — this is a new leaf
        this.right  = null;
    }

    @Override
    public String toString() {
        return "[ISBN: " + isbn + "] \"" + title + "\" by " + author;
    }
}
