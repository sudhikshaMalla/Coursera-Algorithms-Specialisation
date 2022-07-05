package src.divideAndConquerSortingSearching;

import java.util.LinkedList;

public class HashTableUsingOpenAddressing {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(10);

        System.out.println("Is hashtable empty? " + hashTable.isEmpty());
        hashTable.display();
        System.out.println();

        hashTable.insert("Hello World!");
        hashTable.insert("Java");
        hashTable.insert("Object Oriented Programming");
        hashTable.insert("Problem Solving");
        hashTable.insert("Data Structures");
        hashTable.insert("Algorithms");
        hashTable.insert("HashTable");

        System.out.println("Size of hashtable is : " + hashTable.length());
        hashTable.display();
        System.out.println();

        hashTable.remove("Hello World!");
        hashTable.display();
        System.out.println();

        hashTable.remove("React");
    }
}

class HashTable {
    private int capacity;
    private String hashTable[];
    private int length;

    public HashTable(int capacity) {
        this.capacity = capacity;
        hashTable = new String[capacity];
        length = 0;
    }

    public void insert(String value) {
        if(length < capacity) {
            int key = getHashKey(value);
            while(hashTable[key] != null) {
                key = (key + 1)%capacity;
            }

            hashTable[key] = value;
            ++ length;
        }
        else {
            System.out.println("Hash Table is full!!");
        }
    }

    private int getHashKey(String value) {
        int key = 0;
        for(int i=0; i<value.length(); i++) {
            key += value.charAt(i);
        }
        key /= value.length();
        return key% capacity;
    }

    public void remove(String value) {
        int key = getHashKey(value);
        int count = 0;
        while(count<capacity && hashTable[key] != value) {
            key = (key + 1)%capacity;
            ++count;
        }
        if(count == capacity) {
            System.out.println("Element is not present in hash table!");
            return;
        }
        hashTable[key] = null;
        -- length;
    }

    public int length() {
        return length;
    }

    public boolean isEmpty() {
        return length==0;
    }

    public void display() {
        for(int i=0; i<capacity; i++) {
            System.out.println(i + " : " + hashTable[i]);
        }
    }
}