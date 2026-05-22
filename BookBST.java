import java.util.ArrayList;

public class BookBST {
    private Book root;

    public void insert(long isbn, String t, String a) {
        root = ins(root, isbn, t, a);
    }

    private Book ins(Book r, long i, String t, String a) {
        // If current node is null, create and insert new book
        if (r == null) return new Book(i, t, a);

        // If new ISBN is smaller, insert into left subtree
        if (i < r.isbn) {
            r.left = ins(r.left, i, t, a);

            // If new ISBN is larger, insert into right subtree
        } else if (i > r.isbn) {
            r.right = ins(r.right, i, t, a);
        }
        return r;
    }

    public Book search(long isbn) { 
        return sea(root, isbn); 
    }

    // O(log n) time complexity for search operation
    private Book sea(Book r, long isbn) { 
        // Both base cases: r == null (not found) and r.isbn == isbn (found)
        if (r == null || r.isbn == isbn) { 
            return r;
        }

        // Recursively search the left or right subtree based on the ISBN value
        return (isbn < r.isbn) ? sea(r.left, isbn) : sea(r.right, isbn); 
    }

    // Search by title - O(n) full tree traversal, returns all matches
    public ArrayList<Book> searchByTitle(String keyword) {
        ArrayList<Book> results = new ArrayList<>();
        searchTitle(root, keyword.toLowerCase(), results);
        return results;
    }
 
    // Traverse every node and check if title contains the keyword
    private void searchTitle(Book r, String keyword, ArrayList<Book> results) {
        if (r == null) return;
        if (r.title.toLowerCase().contains(keyword)) {
            results.add(r);
        }
        searchTitle(r.left, keyword, results);
        searchTitle(r.right, keyword, results);
    }
}

