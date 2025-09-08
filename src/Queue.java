import java.util.NoSuchElementException;

public class Queue implements DataStructure {
    private Node front;  // head
    private Node rear;   // tail
    private int size;

    public Queue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    @Override
    public boolean hasPop() {
        return front != null;
    }

    @Override
    public void push(Object value) { // enqueue
        Node newNode = new Node(value);
        if (rear == null) { // queue empty
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("[ENQUEUE] => " + value);
        printQueue();
    }

    @Override
    public Object pop() { // dequeue
        if (!hasPop()) throw new NoSuchElementException("Queue is empty!");
        Object value = front.value;
        front = front.next;
        if (front == null) rear = null; // queue became empty
        size--;
        System.out.println("[DEQUEUE] => " + value);
        printQueue();
        return value;
    }

    @Override
    public int getSize() {
        return size;
    }

    public void printQueue() {
        if (front == null) {
            System.out.println("Queue is empty");
            return;
        }

        System.out.println("Queue ------");
        Node current = front;
        while (current != null) {
            System.out.println("  [" + current.value + "]");
            current = current.next;
        }
        System.out.println("------------");
    }

    public void swap(int index1, int index2) {
        if (index1 < 0 || index2 < 0 || index1 >= size || index2 >= size) {
            System.out.println("!!! Invalid Index swap !!!");
            return;
        }

        if (index1 == index2) {
            System.out.println("!!! Index tak bisa sama !!!");
            return;
        }

        Node node1 = front;
        for (int i = 0; i < index1; i++) {
            node1 = node1.next;
        }

        Node node2 = front;
        for (int i = 0; i < index2; i++) {
            node2 = node2.next;
        }

        Object tempData = node1.value;
        node1.value = node2.value;
        node2.value = tempData;

        System.out.println("Swap berhasil!");
    }

    @Override
    public Node getFirstNode() {
        return front;
    }
}