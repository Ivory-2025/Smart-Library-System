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

    public Book search(int i) {
        return null;
    }

    private Book sea(Book r, int i) {
        return null;
    }
}