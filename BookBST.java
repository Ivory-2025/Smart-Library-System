public class BookBST {
    private Book root;

    public void insert(int isbn, String t, String a) {
        root = ins(root, isbn, t, a);
    }

    private Book ins(Book r, int i, String t, String a) {
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

 public Book search(int isbn) { 
        return sea(root, isbn); 
    }

    // O(log n) time complexity for search operation
    private Book sea(Book r, int isbn) { 
        // Both base cases: r == null (not found) and r.isbn == isbn (found)
        if (r == null || r.isbn == isbn) { 
            return r;
        }

        // Recursively search the left or right subtree based on the ISBN value
        return (isbn < r.isbn) ? sea(r.left, isbn) : sea(r.right, isbn); 
    }
}

