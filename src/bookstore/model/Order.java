package bookstore.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String id;
    private Book[] books;
    private int bookCount;

    public Order() {
        this.id = generateRandomId();
        this.books = new Book[50];
        this.bookCount = 0;
    }

    private String generateRandomId() {
        long time = System.currentTimeMillis();
        return "ORD" + (time % 100000);
    }

    public void addBook(Book book) {
        if (bookCount < books.length) {
            books[bookCount] = book;
            bookCount++;
        }
    }

    public void sortBooksByTitle() {
        for (int i = 0; i < bookCount - 1; i++) {
            for (int j = i + 1; j < bookCount; j++) {
                if (books[i].getTitle().compareTo(books[j].getTitle()) > 0) {
                    Book temp = books[i];
                    books[i] = books[j];
                    books[j] = temp;
                }
            }
        }
    }

    public void printOrder() {
        System.out.println("Order ID: " + id);
        System.out.println("Books in order:");
        double totalPrice = 0;
        for (int i = 0; i < bookCount; i++) {

            System.out.println((i+1) + ". Book: " + books[i].getTitle() +
                    " | Author: " + books[i].getAuthor() +
                    " | Price: $" + books[i].getPrice());
            totalPrice += books[i].getPrice();
        }
        System.out.println("Total price: $" + totalPrice);
    }

    public List<Book> getBooks() {
        List<Book> bookList = new ArrayList<>();
        for (int i = 0; i < bookCount; i++) {
            bookList.add(books[i]);
        }
        return bookList;
    }
}
