package com.example.introspring.structures;

import java.util.ArrayList;
import java.util.List;

public class LinkedList<T> {
    private Node<T> head;
    private int size;

    public void add(T data) {
        Node<T> newNode = new Node<>(data);

        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }

        size++;
    }

    public int size() {
        return size;
    }

    // Spring no sabe convertir esta lista a JSON, por eso se responde con una List de Java
    public List<T> toList() {
        List<T> result = new ArrayList<>();
        Node<T> current = head;

        while (current != null) {
            result.add(current.getData());
            current = current.getNext();
        }

        return result;
    }
}
