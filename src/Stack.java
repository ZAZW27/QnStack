import java.util.ArrayList;
import java.util.EmptyStackException;

public class Stack {
    public static class Node { // <-- Made public for direct access
        final public Object value;
        public Node next;

        public Node(Object value) {
            this.value = value;
        }
    }

    public Node first;
    private int size;

    public Stack() {
        this.first = null;
        this.size = 0;
    }

    public boolean hasPop() {
        return first != null;
    }

    public void push(Object value) {
        Node newNode = new Node(value);
        newNode.next = first;
        first = newNode;
        size++;
    }

    public Object pop() {
        if (!hasPop()) throw new EmptyStackException();
        Object value = first.value;
        first = first.next;
        size--;
        return value;
    }

    public int getSize() {
        return size;
    }
}
