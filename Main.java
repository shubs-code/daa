import java.util.*;

interface DataStructure {
    void create();
    void insert(int index, int value);
    void delete(int index);
    void update(int index, int value);
    void search(int value);
    void reverse();
    void display();
    int getComparisons();
    int getShifts();
}

class ArrayStructure implements DataStructure {
    private int[] arr;
    private int size;
    private int comparisons = 0, shifts = 0;

    public void create() {
        arr = new int[100];
        size = 0;
        comparisons = 0;
        shifts = 0;
    }

    public void insert(int index, int value) {
        if (index < 0 || index > size) return;
        for (int i = size; i > index; i--) {
            arr[i] = arr[i - 1];
            shifts++;
        }
        arr[index] = value;
        size++;
    }

    public void delete(int index) {
        if (index < 0 || index >= size) return;
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
            shifts++;
        }
        size--;
    }

    public void update(int index, int value) {
        if (index >= 0 && index < size) {
            arr[index] = value;
        }
    }

    public void search(int value) {
        comparisons = 0;
        for (int i = 0; i < size; i++) {
            comparisons++;
            if (arr[i] == value) {
                System.out.println("Found at index " + i);
                return;
            }
        }
        System.out.println("Not Found");
    }

    public void reverse() {
        for (int i = 0; i < size / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[size - 1 - i];
            arr[size - 1 - i] = temp;
        }
    }

    public void display() {
        for (int i = 0; i < size; i++) System.out.print(arr[i] + " ");
        System.out.println();
    }

    public int getComparisons() { return comparisons; }
    public int getShifts() { return shifts; }
}

class SinglyLinkedList implements DataStructure {
    class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }
    private Node head;
    private int comparisons = 0, shifts = 0;

    public void create() {
        head = null;
        comparisons = 0;
        shifts = 0;
    }

    public void insert(int index, int value) {
        Node newNode = new Node(value);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            return;
        }
        Node temp = head;
        for (int i = 0; i < index - 1 && temp != null; i++) temp = temp.next;
        if (temp == null) return;
        newNode.next = temp.next;
        temp.next = newNode;
    }

    public void delete(int index) {
        if (index == 0 && head != null) {
            head = head.next;
            return;
        }
        Node temp = head;
        for (int i = 0; i < index - 1 && temp != null; i++) temp = temp.next;
        if (temp == null || temp.next == null) return;
        temp.next = temp.next.next;
    }

    public void update(int index, int value) {
        Node temp = head;
        for (int i = 0; i < index && temp != null; i++) temp = temp.next;
        if (temp != null) temp.data = value;
    }

    public void search(int value) {
        Node temp = head;
        comparisons = 0;
        int index = 0;
        while (temp != null) {
            comparisons++;
            if (temp.data == value) {
                System.out.println("Found at index " + index);
                return;
            }
            index++;
            temp = temp.next;
        }
        System.out.println("Not Found");
    }

    public void reverse() {
        Node prev = null, current = head, next;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public int getComparisons() { return comparisons; }
    public int getShifts() { return shifts; }
}

class DoublyLinkedList implements DataStructure {
    class Node {
        int data;
        Node next, prev;
        Node(int data) { this.data = data; }
    }
    private Node head;
    private int comparisons = 0, shifts = 0;

    public void create() {
        head = null;
        comparisons = 0;
        shifts = 0;
    }

    public void insert(int index, int value) {
        Node newNode = new Node(value);
        if (index == 0) {
            newNode.next = head;
            if (head != null) head.prev = newNode;
            head = newNode;
            return;
        }
        Node temp = head;
        for (int i = 0; i < index - 1 && temp != null; i++) temp = temp.next;
        if (temp == null) return;
        newNode.next = temp.next;
        if (temp.next != null) temp.next.prev = newNode;
        temp.next = newNode;
        newNode.prev = temp;
    }

    public void delete(int index) {
        if (index == 0 && head != null) {
            head = head.next;
            if (head != null) head.prev = null;
            return;
        }
        Node temp = head;
        for (int i = 0; i < index && temp != null; i++) temp = temp.next;
        if (temp == null) return;
        if (temp.prev != null) temp.prev.next = temp.next;
        if (temp.next != null) temp.next.prev = temp.prev;
    }

    public void update(int index, int value) {
        Node temp = head;
        for (int i = 0; i < index && temp != null; i++) temp = temp.next;
        if (temp != null) temp.data = value;
    }

    public void search(int value) {
        Node temp = head;
        comparisons = 0;
        int index = 0;
        while (temp != null) {
            comparisons++;
            if (temp.data == value) {
                System.out.println("Found at index " + index);
                return;
            }
            index++;
            temp = temp.next;
        }
        System.out.println("Not Found");
    }

    public void reverse() {
        Node current = head, temp = null;
        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }
        if (temp != null) head = temp.prev;
    }

    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public int getComparisons() { return comparisons; }
    public int getShifts() { return shifts; }
}

class StackStructure implements DataStructure {
    private Stack<Integer> stack = new Stack<>();
    private int comparisons = 0, shifts = 0;

    public void create() {
        stack.clear();
        comparisons = 0;
        shifts = 0;
    }

    public void insert(int index, int value) {
        stack.push(value);
    }

    public void delete(int index) {
        if (!stack.isEmpty()) stack.pop();
    }

    public void update(int index, int value) {
        if (!stack.isEmpty()) {
            stack.pop();
            stack.push(value);
        }
    }

    public void search(int value) {
        comparisons = 0;
        for (int val : stack) {
            comparisons++;
            if (val == value) {
                System.out.println("Found");
                return;
            }
        }
        System.out.println("Not Found");
    }

    public void reverse() {
        Collections.reverse(stack);
    }

    public void display() {
        System.out.println(stack);
    }

    public int getComparisons() { return comparisons; }
    public int getShifts() { return shifts; }
}

class QueueStructure implements DataStructure {
    private Queue<Integer> queue = new LinkedList<>();
    private int comparisons = 0, shifts = 0;

    public void create() {
        queue.clear();
        comparisons = 0;
        shifts = 0;
    }

    public void insert(int index, int value) {
        queue.add(value);
    }

    public void delete(int index) {
        if (!queue.isEmpty()) queue.poll();
    }

    public void update(int index, int value) {
        List<Integer> list = new ArrayList<>(queue);
        if (index >= 0 && index < list.size()) list.set(index, value);
        queue = new LinkedList<>(list);
    }

    public void search(int value) {
        comparisons = 0;
        for (int val : queue) {
            comparisons++;
            if (val == value) {
                System.out.println("Found");
                return;
            }
        }
        System.out.println("Not Found");
    }

    public void reverse() {
        List<Integer> list = new ArrayList<>(queue);
        Collections.reverse(list);
        queue = new LinkedList<>(list);
    }

    public void display() {
        System.out.println(queue);
    }

    public int getComparisons() { return comparisons; }
    public int getShifts() { return shifts; }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DataStructure ds = null;

        while (true) {
            System.out.println("Choose Data Structure: 1.Array 2.SinglyLinkedList 3.DoublyLinkedList 4.Stack 5.Queue 0.Exit");
            int choice = sc.nextInt();
            if (choice == 0) break;
            switch (choice) {
                case 1: ds = new ArrayStructure(); break;
                case 2: ds = new SinglyLinkedList(); break;
                case 3: ds = new DoublyLinkedList(); break;
                case 4: ds = new StackStructure(); break;
                case 5: ds = new QueueStructure(); break;
                default: continue;
            }

            ds.create();
            while (true) {
                System.out.println("Operations: 1.Insert 2.Delete 3.Update 4.Search 5.Reverse 6.Display 7.Back");
                int op = sc.nextInt();
                if (op == 7) break;
                int idx, val;
                switch (op) {
                    case 1:
                        System.out.print("Index and value: ");
                        idx = sc.nextInt(); val = sc.nextInt();
                        ds.insert(idx, val);
                        break;
                    case 2:
                        System.out.print("Index: ");
                        idx = sc.nextInt();
                        ds.delete(idx);
                        break;
                    case 3:
                        System.out.print("Index and new value: ");
                        idx = sc.nextInt(); val = sc.nextInt();
                        ds.update(idx, val);
                        break;
                    case 4:
                        System.out.print("Value to search: ");
                        val = sc.nextInt();
                        ds.search(val);
                        break;
                    case 5:
                        ds.reverse();
                        break;
                    case 6:
                        ds.display();
                        System.out.println("Comparisons: " + ds.getComparisons() + ", Shifts: " + ds.getShifts());
                        break;
                }
            }
        }
    }
}
