import java.util.Scanner;
import java.util.ArrayList;

class SmartLibrary implements LibraryADT {
    private BookBST catalogue = new BookBST();
    private BorrowStack history = new BorrowStack();
    private FineManager fineManager = new FineManager();

    public void addBook(long isbn, String title, String author) {
        catalogue.insert(isbn, title, author);
        System.out.println("Book added: [ISBN: " + isbn + "] " + title + " by " + author);
    }
    
    //search by ISBN
    public void searchBook(long isbn) {
        Book b = catalogue.search(isbn);
        if (b != null) {
            System.out.println("Found: " + b.toString());
        } else {
            System.out.println("Book not found.");
        }
    }

    //search by title
    public void searchBookByTitle(String keyword) {
        ArrayList<Book> results = catalogue.searchByTitle(keyword);
        if (results.isEmpty()) {
            System.out.println("No books found with title containing: \"" + keyword + "\"");
        } else {
            System.out.println("Found " + results.size() + " result(s):");
            for (Book b : results) {
                System.out.println("  " + b.toString());
            }
        }
    }

    //borrow book
    public void borrowBook(long isbn) {
        Book b = catalogue.search(isbn);
        if (b != null) {
            history.pushBorrowRecord(b);
        } else {
            System.out.println("Book not in catalogue.");
        }
    }

    //view borrowing history in LIFO oder
    public void viewLatestHistory() {
        history.displayLIFOHistory();
    }

    //ISBN validation (only 10 or 13 digits)
    private boolean isValidIsbn(long isbn) {
        String isbnStr = String.valueOf(isbn);
        return isbnStr.length() == 10 || isbnStr.length() == 13;
    }

    // Helper to safely read ISBN from Scanner by returning -1 meaning invalid
    private long readIsbn(Scanner sc) {
        if (!sc.hasNextLong()) {
            System.out.println("Invalid ISBN. Must be a number.");
            sc.next();
            return -1; 
        }
        long isbn = sc.nextLong();
        sc.nextLine();
        if (!isValidIsbn(isbn)) {
            System.out.println("Invalid ISBN. Must be exactly 10 or 13 digits.");
            return -1; 
        }
        return isbn;
    }

    //main menu loop
    public void runMenu() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 7) {
            printMenu();
            System.out.print("Choice: ");

            // Input validation 
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
                continue;
            }

            choice = sc.nextInt();
            sc.nextLine();

            if (choice < 1 || choice > 7) {
                System.out.println("Invalid option.");
                continue;
            }

            handleChoice(choice, sc);
        }

        sc.close();
    }

    private void printMenu() {
        
        System.out.println("\n--- Smart Library Menu ---");
        System.out.println("1. Add Book");
        System.out.println("2. Search by ISBN");
        System.out.println("3. Search by Title");
        System.out.println("4. Borrow Book (Stack)");
        System.out.println("5. View Borrow History");
        System.out.println("6. Fine Management");
        System.out.println("7. Exit");
    }

    private void handleChoice(int choice, Scanner sc) {
        switch (choice) {

            case 1:
                System.out.print("Enter ISBN(10 or 13 digits): ");
                long addIsbn = readIsbn(sc);
                if (addIsbn == -1) 
                    break;

                System.out.print("Enter Title: ");
                String title = sc.nextLine().trim();
                if (title.isEmpty()) {
                    System.out.println("Title cannot be empty.");
                    break;
                }

                System.out.print("Enter Author: ");
                String author = sc.nextLine().trim();
                if (author.isEmpty()) {
                    System.out.println("Author cannot be empty.");
                    break;
                }

                addBook(addIsbn, title, author);
                break;

            case 2:
                System.out.print("Enter ISBN to search (10 or 13 digits): ");
                long searchIsbn = readIsbn(sc);
                if (searchIsbn == -1) 
                    break;
                searchBook(searchIsbn);
                break;

            // Search by Title
            case 3: 
                System.out.print("Enter title keyword: ");
                String keyword = sc.nextLine().trim();
                if (keyword.isEmpty()) {
                    System.out.println("Keyword cannot be empty.");
                    break;
                }
                searchBookByTitle(keyword);
                break;

            //borrow book
            case 4:
                System.out.print("Enter ISBN to borrow (10 or 13 digits): ");
                long borrowIsbn = readIsbn(sc);
                if (borrowIsbn == -1) break;
                borrowBook(borrowIsbn);
                break;

            case 5:
                viewLatestHistory();
                break;

            // Fine Management
            case 6: 
                handleFineMenu(sc);
                break;
        
            case 7:
                System.out.println("Goodbye!");
                break;

            default:
                System.out.println("Invalid option.");
        }
    }

    private void handleFineMenu(Scanner sc) {
        System.out.println("\n--- Fine Management ---");
        System.out.println("1. Add Fine Record");
        System.out.println("2. View All Fines");
        System.out.println("3. Back to Main Menu");
        System.out.print("Choice: ");
 
        if (!sc.hasNextInt()) {
            System.out.println("Invalid input.");
            sc.next();
            return;
        }
 
        int fineChoice = sc.nextInt();
        sc.nextLine();
 
        if (fineChoice == 1) {
            System.out.print("Enter Student Name: ");
            String name = sc.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Student name cannot be empty.");
                return;
            }
 
            System.out.print("Enter ISBN of overdue book (10 or 13 digits): ");
            long fineIsbn = readIsbn(sc);
            if (fineIsbn == -1) 
                return;
 
            Book b = catalogue.search(fineIsbn);
            if (b == null) {
                System.out.println("Book not found in catalogue.");
                return;
            }
 
            System.out.print("Enter number of days overdue: ");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid number.");
                sc.next();
                return;
            }
            int days = sc.nextInt();
            sc.nextLine();
            if (days <= 0) {
                System.out.println("Days overdue must be at least 1.");
                return;
            }
 
            fineManager.addFine(name, b, days);
 
        } else if (fineChoice == 2) {
            fineManager.displayAllFines();
 
        } else if (fineChoice == 3) {
            System.out.println("Returning to main menu.");
 
        } else {
            System.out.println("Invalid option.");
        }
    }
}