public interface DataStructure {
    void push(Object value);
    Object pop();
    boolean hasPop();
    int getSize();
    Node getFirstNode();
}