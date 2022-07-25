package ua.cars.entity;

import ua.cars.entity.vehicle.Vehicle;

import java.time.Instant;
import java.util.*;
import java.util.function.Consumer;

public class TransportGarage<T extends Vehicle> {

    private int restNumber = 0; // Количество рестайлингов (+1 рестайлинг если пользователь решает изменить параметри транспорта при помощи метода set())
    private Instant timeFirstCarADD;// Системное время добавления пераого транспорта в коллекцию, записывается один раз после чего не изменячется
    private Instant timeLastCarADD;// Системное время добавления последнего добавленного транспорта, перезаписывается каждый раз придобавлении нового транспорта

    public Optional<Instant> getTimeFirstCarADD() {
        return Optional.of(timeFirstCarADD);
    }

    public Optional<Instant> getTimeLastCarADD() {
        return Optional.of(timeLastCarADD);
    }

    public int getRestNumber() {
        return restNumber;
    }

    private class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node<T> head = null;

    public int size() {
        Node<T> p;
        int size = 0;
        for (p = head; p != null; p = p.next) {
            size++;
        }
        return size;
    }

    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    public void add(T o) {
        if (isEmpty()) {
            timeFirstCarADD = Instant.now();
            head = new Node<T>(o);
        } else {
            timeLastCarADD = Instant.now();
            Node<T> p = head;
            Node<T> node = new Node<T>(o);
            while (p.next != null) {
                p = p.next;
            }
            p.next = node;
            node.next = null;
        }
    }

    public boolean remove(T o) {
        Node<T> p = head, p1 = null;
        boolean have = false;
        if (isEmpty()) {
            return false;
        }
        while (p != null) {
            if (p.value.equals(o)) {
                if (p1 == null) {
                    head = head.next;
                } else {
                    p1.next = p.next;
                }
                have = true;
            }
            p1 = p;
            p = p.next;
        }
        return have;
    }

    public T get(int index) {
        int i = -1;
        if (isEmpty()) {
            return null;
        }
        if (index < 0 || index > size()) {
            return null;
        }
        Node<T> p = head;
        while (p != null) {
            i++;
            if (i == index) {
                return p.value;
            }
            p = p.next;
        }
        return null;
    }

    public T set(int index, T element) {
        restNumber++;
        int i = -1;
        if (isEmpty()) {
            add(element);
            return null;
        }
        if (index < 0 || index > size()) {
            return null;
        }
        Node<T> p = head;
        T o = null;
        while (p != null) {
            i++;
            if (i == index) {
                o = p.value;
                p.value = element;
                return o;
            }
            p = p.next;
        }
        return null;
    }

    public void add(int index, T element) {
        int i = -1;
        if (isEmpty()) {
            this.add(element);
            return;
        }
        if (index < 0 || index > size()) {
            return;
        }
        Node<T> p = head, p1 = null;
        while (p != null) {
            i++;
            if (i == index) {
                Node<T> newNode = new Node<T>(element);
                if (p1 == null) {
                    newNode.next = head;
                    head = newNode;
                } else {
                    p1.next = newNode;
                    newNode.next = p;
                }
            }
            p1 = p;
            p = p.next;
        }
    }

    public T remove(int index) {
        if (isEmpty()) {
            return null;
        }
        if (index < 0 || index > size()) {
            return null;
        }
        Node<T> p = head, p1 = null;
        int i = -1;
        while (p != null) {
            i++;
            if (i == index) {
                if (p1 == null) {
                    head = head.next;
                } else {
                    p1.next = p.next;
                }
                return p.value;
            }
            p1 = p;
            p = p.next;
        }
        return null;
    }

    public void forEach(Consumer<T> action) {
        Objects.requireNonNull(action);
        Node<T> p;
        for (p = head; p != null; p = p.next) {
            action.accept(p.value);
        }
    }
}
