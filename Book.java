public class Book {
    long isbn;   
    String title;
    String author;
    boolean isAvailable;

    //BST node pointers (used by BookBST only)
    Book left;
    Book right;

    public Book(long isbn, String title, String author) {
        this.isbn   = isbn;
        this.title  = title;
        this.author = author;
        this.isAvailable = true;
        this.left   = null;   
        this.right  = null;
    }

    @Override
    public String toString() {
        return "[ISBN: " + isbn + "] \"" + title + "\" by " + author;
    }
}
