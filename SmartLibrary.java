import java.util.Scanner;

class SmartLibrary implements LibraryADT {
    private BookBST catalogue = new BookBST();
    private BorrowStack history = new BorrowStack();

    public void addBook(int isbn, String title, String author) {
        catalogue.insert(isbn, title, author);
        System.out.println("Book added: [ISBN: " + isbn + "] " + title + " by " + author);
    }

    public void searchBook(int isbn) {
        Book b = catalogue.search(isbn);
        if (b != null) {
            System.out.println("Found: " + b.toString());
        } else {
            System.out.println("Book not found.");
        }
    }

    public void borrowBook(int isbn) {
        Book b = catalogue.search(isbn);
        if (b != null) {
            history.pushBorrowRecord(b);
        } else {
            System.out.println("Book not in catalogue.");
        }
    }

    public void viewLatestHistory() {
        history.displayLIFOHistory();
    }

    public void runMenu() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 5) {
            printMenu();
            System.out.print("Choice: ");

            // Input validation - handles non-integer input
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
                continue;
            }

            choice = sc.nextInt();
            sc.nextLine();

            if (choice < 1 || choice > 5) {
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
        System.out.println("2. Search");
        System.out.println("3. Borrow");
        System.out.println("4. History");
        System.out.println("5. Exit");
    }

    private void handleChoice(int choice, Scanner sc) {
        switch (choice) {
            case 1:
                System.out.print("Enter ISBN: ");
                // Input validation
                if (!sc.hasNextInt()) {
                    System.out.println("Invalid ISBN.");
                    sc.next();
                    break;
                }
                int isbn = sc.nextInt();
                sc.nextLine();

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

                addBook(isbn, title, author);
                break;

            case 2:
                System.out.print("Enter ISBN to search: ");
                if (!sc.hasNextInt()) {
                    System.out.println("Invalid ISBN.");
                    sc.next();
                    break;
                }
                searchBook(sc.nextInt());
                sc.nextLine();
                break;

            case 3:
                System.out.print("Enter ISBN to borrow: ");
                if (!sc.hasNextInt()) {
                    System.out.println("Invalid ISBN.");
                    sc.next();
                    break;
                }
                borrowBook(sc.nextInt());
                sc.nextLine();
                break;

            case 4:
                viewLatestHistory();
                break;

            case 5:
                System.out.println("Goodbye!");
                break;

            default:
                System.out.println("Invalid option.");
        }
    }
}