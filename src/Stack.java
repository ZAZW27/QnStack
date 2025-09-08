import java.util.EmptyStackException;

public class Stack implements DataStructure {
    private Node first;
    private int size;

    public Stack() {
        this.first = null;
        this.size = 0;
    }

    @Override
    public boolean hasPop() {
        return first != null;
    }

    @Override
    public void push(Object value) {
        Node newNode = new Node(value);
        newNode.next = first;
        first = newNode;
        size++;
        System.out.println("[PUSH] => " + value);
        printStack();
    }

    @Override
    public Object pop() {
        if (!hasPop()) throw new EmptyStackException();
        Object value = first.value;
        first = first.next;
        size--;
        System.out.println("[POP] => " + value);
        printStack();
        return value;
    }

    @Override
    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void printStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }

        System.out.println("Stack ------");
        Node current = first;
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

        int realIndex1 = (size - 1) - index1;
        int realIndex2 = (size - 1) - index2;

        Node node1 = first;
        for (int i = 0; i < realIndex1; i++) {
            node1 = node1.next;
        }

        Node node2 = first;
        for (int i = 0; i < realIndex2; i++) {
            node2 = node2.next;
        }

        Object tempData = node1.value;
        node1.value = node2.value;
        node2.value = tempData;

        System.out.println("Swap berhasil!");
    }

    @Override
    public Node getFirstNode() {
        return first;
    }
}