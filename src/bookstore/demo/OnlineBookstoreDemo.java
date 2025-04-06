package bookstore.demo;

import bookstore.model.Book;
import bookstore.model.Order;
import bookstore.order_management.OrderQueue;
import java.io.*;

public class OnlineBookstoreDemo {
    private Book[] bookCatalog;
    private int bookCount;
    private OrderQueue orderQueue;

    public OnlineBookstoreDemo() {
        bookCatalog = new Book[100];
        bookCount = 0;
        orderQueue = new OrderQueue(50);
    }

    public void addBook(Book book) {
        if (bookCount < bookCatalog.length) {
            bookCatalog[bookCount] = book;
            bookCount++;
            System.out.println("✅ Book added successfully!");
        } else {
            System.out.println("❌ Book catalog is full. Cannot add more books.");
        }
    }

    public void addNewBook() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("\n=== Adding New Book ===");
            System.out.println("Enter book title:");
            String title = reader.readLine();

            System.out.println("Enter author:");
            String author = reader.readLine();

            System.out.println("Enter price:");
            double price = Double.parseDouble(reader.readLine());

            System.out.println("Enter quantity:");
            int quantity = Integer.parseInt(reader.readLine());

            addBook(new Book(title, author, price, quantity));
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input. Book not added.");
        }
    }

    public void listBooks() {
        if (bookCount == 0) {
            System.out.println("No books in the catalog.");
            return;
        }

        System.out.println("\n📚 Book Catalog:");
        for (int i = 0; i < bookCount; i++) {
            System.out.println((i+1) + ". " + bookCatalog[i]);
        }
    }

    public void sortBooksByTitle() {
        if (bookCount == 0) {
            System.out.println("No books to sort.");
            return;
        }

        for (int i = 0; i < bookCount - 1; i++) {
            for (int j = i + 1; j < bookCount; j++) {
                if (bookCatalog[i].getTitle().compareTo(bookCatalog[j].getTitle()) > 0) {
                    Book temp = bookCatalog[i];
                    bookCatalog[i] = bookCatalog[j];
                    bookCatalog[j] = temp;
                }
            }
        }
    }

    public void createOrder() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Order order = new Order();

        // Display available books before ordering
        System.out.println("\n📚 Available books in the store:");
        listBooks(); // Call method to print book list

        if (bookCount == 0) {
            System.out.println("❌ Cannot create order - no books available in store.");
            return;
        }

        while (true) {
            System.out.println("\nEnter the book title to purchase (or type 'done' to finish): ");
            String title = reader.readLine();

            if (title.equalsIgnoreCase("done")) break;

            Book selectedBook = null;
            for (int i = 0; i < bookCount; i++) {
                if (bookCatalog[i].getTitle().equalsIgnoreCase(title)) {
                    selectedBook = bookCatalog[i];
                    break;
                }
            }

            if (selectedBook != null) {
                if (selectedBook.getQuantity() > 0) {
                    order.addBook(selectedBook);
                    selectedBook.setQuantity(selectedBook.getQuantity() - 1); // Reduce quantity
                    System.out.println("✅ Book '" + selectedBook.getTitle() + "' added to your order. (Remaining: " + selectedBook.getQuantity() + ")");
                } else {
                    System.out.println("❌ Sorry, '" + selectedBook.getTitle() + "' is out of stock!");
                }
            } else {
                System.out.println("⚠️ Book not found. Please enter a valid title.");
            }
        }

        if (order.getBooks().size() > 0) {
            order.sortBooksByTitle();
            orderQueue.enqueue(order);
            System.out.println("\n🎉 Order has been successfully added to the queue!");
        } else {
            System.out.println("\n⚠️ No books were added to the order. Order canceled.");
        }
    }

    public void processNextOrder() {
        if (orderQueue.isEmpty()) {
            System.out.println("❌ No orders in queue to process.");
            return;
        }

        Order order = orderQueue.dequeue();
        System.out.println("\n📋 Processing order:");
        order.printOrder();
        System.out.println("✅ Order processed successfully!");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        OnlineBookstoreDemo bookstore = new OnlineBookstoreDemo();

        System.out.println("==== Welcome to Online Book Store System ====");

        while (true) {
            System.out.println("\n=== Book Store System Menu ===");
            System.out.println("1. Add new book");
            System.out.println("2. View Books List");
            System.out.println("3. Sort books by title");
            System.out.println("4. Create new order");
            System.out.println("5. Process next order");
            System.out.println("6. Exit");
            System.out.print("Your choice: ");

            try {
                int choice = Integer.parseInt(reader.readLine());

                switch (choice) {
                    case 1:
                        bookstore.addNewBook();
                        break;
                    case 2:
                        bookstore.listBooks();
                        break;
                    case 3:
                        bookstore.sortBooksByTitle();
                        System.out.println("✅ Books have been sorted by title.");
                        break;
                    case 4:
                        bookstore.createOrder();
                        break;
                    case 5:
                        bookstore.processNextOrder();
                        break;
                    case 6:
                        System.out.println("Thank you for using the Book Store System. Goodbye!");
                        return;
                    default:
                        System.out.println("❌ Invalid selection. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number.");
            }
        }
    }
}