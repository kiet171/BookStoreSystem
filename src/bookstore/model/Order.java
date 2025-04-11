
package bookstore.model;

import bookstore.data_structures.MyArrayList;

public class Order {
    private String id;
    private MyArrayList<Book> books;

    public Order() {
        this.id = generateRandomId();
        this.books = new MyArrayList<>();
    }

    private String generateRandomId() {
        long time = System.currentTimeMillis();
        return "ORD" + (time % 100000);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void printOrder() {
        System.out.println("Order ID: " + id);
        System.out.println("Books in order:");
        double totalPrice = 0;
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            System.out.println((i + 1) + ". Book: " + b.getTitle() +
                    " | Author: " + b.getAuthor() +
                    " | Price: $" + b.getPrice());
            totalPrice += b.getPrice();
        }
        System.out.println("Total price: $" + totalPrice);
    }

    public MyArrayList<Book> getBooks() {
        return books;
    }
}
