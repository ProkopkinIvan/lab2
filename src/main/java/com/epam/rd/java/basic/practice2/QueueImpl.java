package com.epam.rd.java.basic.practice2;

import java.util.Iterator;

public class QueueImpl implements Queue {

    private Node head = null;
    private Node tail = null;
    private int count;

    static class Node {
        Object data;
        Node next;
        Node prev;

        public Node(Object data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    @Override
    public int size() {
        return count;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl(head);
    }

    private static class IteratorImpl implements Iterator<Object> {

        private final Node current;

        public IteratorImpl(Node node) {
            current = node;
        }

        @Override
        public boolean hasNext() {
            boolean result = false;

            if (current != null && current.next != null) {
                result = true;
            }

            return result;
        }

        @Override
        public Object next() {
            return current.next;
        }

    }

    @Override
    public void enqueue(Object element) {
        Node newNode = new Node(element);

        if (head == null) {
            head = newNode;
        } else if (tail == null) {
            tail = newNode;
            tail.prev = head;
            head.next = tail;
        } else {
            tail.next = newNode;
            newNode.prev = tail;

            tail = newNode;
        }

        count++;
    }

    @Override
    public Object dequeue() {
        if (head != null) {
            head = head.next;
            head.prev = null;

            if (head.equals(tail)) {
                tail = null;
            }

            if (count > 0) {
                count--;
            }
        }

        return true;
    }

    @Override
    public Object top() {
        Object result = null;

        if (head != null) {
            result = head.data;
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node newNode = head;

        while (newNode != null) {
            builder.append(newNode.data.toString());

            newNode = newNode.next;

            if (newNode != null) {
                builder.append(", ");
            }
        }

        return "[" + builder.toString() + "]";
    }

    public static void main(String[] args) {
        QueueImpl queue = new QueueImpl();
        System.out.println(queue);

        queue.enqueue("last");
        System.out.println("After enqueue:");
        System.out.println(queue.size());

        queue.enqueue("new elem");
        System.out.println("After enqueue:");
        System.out.println(queue);

        System.out.println("Top method:");
        System.out.println(queue.top());

        System.out.println("Size:");
        System.out.println(queue.size());

        queue.clear();
        System.out.println("After clear:");
        System.out.println(queue);

        System.out.println("Size:");
        System.out.println(queue.size());
    }

}