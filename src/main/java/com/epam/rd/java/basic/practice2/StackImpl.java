package com.epam.rd.java.basic.practice2;

import java.util.Iterator;

public class StackImpl implements Stack {

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
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        public StackImpl.Node current = head;

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
    public void push(Object element) {
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
    public Object pop() {
        Object result = null;
        if (tail != null) {
            Node elementToBeReturned = tail;

            tail = elementToBeReturned.prev;
            tail.next = null;

            if (count > 0) {
                count--;
            }

            result = elementToBeReturned.data;
        }

        return result;
    }

    @Override
    public Object top() {
        Object result = null;
        if (tail != null) {
            result = tail.data;
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node currentNode = head;

        while (currentNode != null) {
            result.append(currentNode.data.toString());

            currentNode = currentNode.next;

            if (currentNode != null) {
                result.append(", ");
            }
        }

        return "[" + result.toString() + "]";
    }

    public static void main(String[] args) {
        StackImpl stack = new StackImpl();

        stack.push("A");
        stack.push("B");
        stack.push("C");
        System.out.println(stack);

        System.out.println("Size: ");
        System.out.println(stack.size());

        System.out.println("Top: ");
        System.out.println(stack.top());

        System.out.println("Pop: ");
        System.out.println(stack.pop());
        System.out.println("After pop: ");
        System.out.println(stack);

        System.out.println("Size: ");
        System.out.println(stack.size());
    }

}