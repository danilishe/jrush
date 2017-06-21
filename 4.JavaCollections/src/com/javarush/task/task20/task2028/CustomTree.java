package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {

    static class Entry<T> implements Serializable {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String name) {
            elementName = name;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        void checkChildren() {
            availableToAddRightChildren = rightChild == null;
            availableToAddLeftChildren = leftChild == null;
        }

        boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }

    Entry<String> root = new Entry<>("0");

    public static void main(String[] args) {
        List<String> list = new CustomTree();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        //System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
        list.remove("5");
        //System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("11"));

    }

    @Override
    public int size() {
        Queue<Entry<String>> queue = new LinkedList<>();
        int size = 0;
        queue.add(root);

        while (!queue.isEmpty()) {
            Entry<String> currentEntry = queue.poll();
            if (currentEntry.leftChild != null) {
                size++;
                queue.add(currentEntry.leftChild);
            }
            if (currentEntry.rightChild != null) {
                size++;
                queue.add(currentEntry.rightChild);
            }
        }
        return size;
    }

    public boolean remove(Object elementName) {

        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        String name = (String) elementName;
        while (!queue.isEmpty()) {
            Entry<String> currentEntry = queue.poll();
            if (currentEntry.leftChild != null && currentEntry.leftChild.elementName.equals(name)) {
                currentEntry.leftChild = null;
                return true;
            } else if (currentEntry.rightChild != null && currentEntry.rightChild.elementName.equals(name)) {
                currentEntry.rightChild = null;
                return true;
            }
            if (currentEntry.leftChild != null) queue.add(currentEntry.leftChild);
            if (currentEntry.rightChild != null) queue.add(currentEntry.rightChild);
        }
        return false;
    }

    public String getParent(String elementName) {
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Entry<String> currentEntry = queue.poll();
            if (currentEntry.leftChild != null && currentEntry.leftChild.elementName.equals(elementName)) {
                return currentEntry.elementName;
            } else if (currentEntry.rightChild != null && currentEntry.rightChild.elementName.equals(elementName)) {
                return currentEntry.elementName;
            } else {
                if (currentEntry.leftChild != null) queue.add(currentEntry.leftChild);
                if (currentEntry.rightChild != null) queue.add(currentEntry.rightChild);
            }
        }
        return null;
    }

    public boolean add(String elementName) {
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);

        Entry<String> newEntry = new Entry<>(elementName);
        while (!queue.isEmpty()) {
            Entry<String> currentEntry = queue.poll();
            currentEntry.checkChildren();

            if (currentEntry.isAvailableToAddChildren()) {
                newEntry.parent = currentEntry;
                if (currentEntry.availableToAddLeftChildren) {
                    currentEntry.leftChild = newEntry;
                } else {
                    currentEntry.rightChild = newEntry;
                }
                return true;
            } else {
                queue.add(currentEntry.leftChild);
                queue.add(currentEntry.rightChild);
            }
        }
        return false;
    }

    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }


}
