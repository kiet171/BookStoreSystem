package bookstore.model;

public class Book {
    private String title;
    private String author;
    private double price;
    private int quantity;  // New field for quantity

    // Constructor with quantity
    public Book(String title, String author, double price, int quantity) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and setters for title, author, price, and quantity
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Book: " + title + " | Author: " + author + " | Price: $" + price + " | Quantity: " + quantity;
    }
}

