package bookstore.order_management;

import bookstore.model.Order;

public class OrderQueue {
    private Order[] queue;
    private int front, rear, size, capacity;

    public OrderQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new Order[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(Order order) {
        if (isFull()) {
            System.out.println("Queue full! Cannot add new order.");
            return;
        }
        rear = (rear + 1) % capacity;
        queue[rear] = order;
        size++;
    }

    public Order dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return null;
        }
        Order order = queue[front];
        front = (front + 1) % capacity;
        size--;
        return order;
    }

    public void printQueue() {
        System.out.println("List of orders in queue:");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            queue[index].printOrder();
        }
    }
}


