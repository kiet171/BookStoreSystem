package bookstore.demo;

import bookstore.model.Book;
import bookstore.model.Order;
import bookstore.data_structures.LinkedQueue;
import bookstore.data_structures.LinkedStack;
import bookstore.data_structures.MyArrayList;
import bookstore.data_structures.MyLinkedList;
import java.io.*;

public class OnlineBookstoreDemo {
    private MyArrayList<Book> bookCatalog;
    private LinkedQueue<Order> orderQueue;
    private LinkedStack<Order> orderHistory;
    private MyLinkedList<Order> processedOrders;

    public OnlineBookstoreDemo() {
        bookCatalog = new MyArrayList<>();
        orderQueue = new LinkedQueue<>();
        orderHistory = new LinkedStack<>();
        processedOrders = new MyLinkedList<>();
    }

    public void addBook(Book book) {
        bookCatalog.add(book);
        System.out.println("✅ Book added successfully!");
    }

    public void listBooks() {
        if (bookCatalog.size() == 0) {
            System.out.println("No books in the catalog.");
            return;
        }

        System.out.println("\n📚 Book Catalog:");
        for (int i = 0; i < bookCatalog.size(); i++) {
            System.out.println((i + 1) + ". " + bookCatalog.get(i));
        }
    }

    public void createOrder() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Order order = new Order();

        listBooks();
        if (bookCatalog.size() == 0) {
            System.out.println("❌ Cannot create order - no books available.");
            return;
        }

        while (true) {
            System.out.println("\nEnter book title to purchase (or 'done' to finish):");
            String title = reader.readLine();
            if (title.equalsIgnoreCase("done")) break;

            Book selected = null;
            for (int i = 0; i < bookCatalog.size(); i++) {
                if (bookCatalog.get(i).getTitle().equalsIgnoreCase(title)) {
                    selected = bookCatalog.get(i);
                    break;
                }
            }

            if (selected != null) {
                if (selected.getQuantity() > 0) {
                    order.addBook(selected);
                    selected.setQuantity(selected.getQuantity() - 1);
                    System.out.println("✅ Book added to order. Remaining: " + selected.getQuantity());
                } else {
                    System.out.println("❌ Out of stock!");
                }
            } else {
                System.out.println("⚠️ Book not found.");
            }
        }

        if (order.getBooks().size() > 0) {
            orderQueue.enqueue(order);
            orderHistory.push(order);
            System.out.println("🎉 Order added to queue and saved to history!");
        } else {
            System.out.println("⚠️ No books were added. Order canceled.");
        }
    }

    public void processNextOrder() {
        if (orderQueue.isEmpty()) {
            System.out.println("❌ No orders in queue.");
            return;
        }

        Order order = orderQueue.dequeue();
        processedOrders.add(order); // ✅ Save processed order in MyLinkedList
        System.out.println("\n📋 Processing order:");
        order.printOrder();
        System.out.println("✅ Order processed successfully!");
    }

    public void showLastOrder() {
        if (orderHistory.isEmpty()) {
            System.out.println("📭 No previous orders.");
        } else {
            System.out.println("🕓 Last created order:");
            orderHistory.peek().printOrder();
        }
    }

    public void viewProcessedOrders() {
        if (processedOrders.isEmpty()) {
            System.out.println("📭 No processed orders yet.");
            return;
        }

        System.out.println("\n📦 Processed Orders:");
        for (int i = 0; i < processedOrders.size(); i++) {
            System.out.println("\nOrder " + (i + 1) + ":");
            processedOrders.get(i).printOrder();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        OnlineBookstoreDemo store = new OnlineBookstoreDemo();

        while (true) {
            System.out.println("\n=== Book Store Menu ===");
            System.out.println("1. Add new book");
            System.out.println("2. View book list");
            System.out.println("3. Sort books by title");
            System.out.println("4. Create new order");
            System.out.println("5. Process next order");
            System.out.println("6. View last order");
            System.out.println("7. View processed orders");
            System.out.println("8. Exit");
            System.out.print("Your choice: ");

            int choice = Integer.parseInt(reader.readLine());
            switch (choice) {
                case 1 -> store.addNewBook();
                case 2 -> store.listBooks();
                case 3 -> store.createOrder();
                case 4 -> store.processNextOrder();
                case 5 -> store.showLastOrder();
                case 6 -> store.viewProcessedOrders();
                case 7 -> {
                    System.out.println("Thanks for using the bookstore system.");
                    return;
                }
                default -> System.out.println("❌ Invalid choice.");
            }
        }
    }

    public void addNewBook() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter book title: ");
        String title = reader.readLine();
        System.out.print("Enter author: ");
        String author = reader.readLine();
        System.out.print("Enter price: ");
        double price = Double.parseDouble(reader.readLine());
        System.out.print("Enter quantity: ");
        int quantity = Integer.parseInt(reader.readLine());
        addBook(new Book(title, author, price, quantity));
    }
}
