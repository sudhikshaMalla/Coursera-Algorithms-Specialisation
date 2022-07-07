package src.divideAndConquerSortingSearching;

import java.util.LinkedList;

public class ChainedHashTable {
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
    }
}

class HashTable {
    private int capacity;
    private LinkedList<String> hashTable[];
    private int length;

    public HashTable(int capacity) {
        this.capacity = capacity;
        hashTable = new LinkedList[capacity];
        for(int i=0; i<capacity; i++) {
            hashTable[i] = new LinkedList<>();
        }
        length = 0;
    }

    public void insert(String value) {
        int key = getHashKey(value);
        hashTable[key].addFirst(value);
        ++length;
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
        for (int i=0; i<hashTable[key].size(); i++) {
            if(hashTable[key].get(i) == value) {
                hashTable[key].remove(i);
                break;
            }
        }
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
            System.out.print(i + " : ");
            LinkedList<String> list = hashTable[i];
            for (int j=0; j<list.size(); j++) {
                if(j!=0) {
                    System.out.print(" -> ");
                }
                System.out.print(list.get(j));
            }
            System.out.println();
        }
    }
}