import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;

public class Stack {
    private static class Node {
        final private Object value;
        private Node next;

        public Node(Object value) {
            this.value = value;
        }
    }

    private Node first;
    private int size; // track size of stack

    public Stack() { // initialize empty stack
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
        size++; // increase size
    }

    public Object pop() {
        if (!hasPop()) {
            throw new EmptyStackException();
        }
        Object value = first.value;
        first = first.next;
        size--; // decrease size
        return value;
    }

    public int getSize() {
        return size;
    }

    // Main method to demonstrate Stack functionality
    public static void main(String[] args) {
        Scanner inputObj =  new Scanner(System.in);
        Stack stack = new Stack();
        while (true){
            System.out.println("========= Pilih aksi =========");
            System.out.println("1. Show data");
            System.out.println("2. push data");
            System.out.println("3. pop data");
            System.out.println("4. Swap data");
            System.out.print("======: ");
            int choice = inputObj.nextInt();
            try{
                switch (choice){
                    case 1:
                        showData(stack);
                        System.out.println("Enter untuk kembali ke menu");
                        inputObj.nextLine();
                        inputObj.nextLine();
                        break;
                    case 2:
                        addData(stack);
                        break;
                    case 3:
                        popData(stack);
                        break;
                    case 4:
                        updateData(stack);
                        break;
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void showData(Stack stack){
        if(isDataEmpty(stack)) return;
        long startTime = System.nanoTime();

        Node current = stack.first;
        int count = stack.size;

        System.out.println("|-----------Data stack-----------|");
        while(current != null){
            System.out.println(count + ". " + current.value + " ");
            current = current.next;
            count--;
        }
        System.out.println("|--------------------------------|");

        System.out.println(Utils.timeSpent(System.nanoTime() - startTime));
    }

    public static void addData(Stack stack){
        Scanner inputObj = new Scanner(System.in);
        while (true){
            System.out.println("Masukkan data (kosongkan untuk break): ");
            String data = inputObj.nextLine();

            long startProgram = System.nanoTime();
            if(data.isEmpty()) break;

            stack.push(Utils.parseInput(data));

            System.out.println(Utils.timeSpent(System.nanoTime() - startProgram));
        }
    }

    public static boolean isDataEmpty(Stack stack){
        long startTime = System.nanoTime();
        if(!stack.hasPop()) System.out.println("!!!!! Data tidak tersedia !!!!!");
        System.out.println(Utils.timeSpent(System.nanoTime() - startTime));
        return !stack.hasPop();
    }

    public static void popData(Stack stack){
        if(isDataEmpty(stack)) return;
        long startTime = System.nanoTime();

        stack.pop();
        System.out.println("Data berhasil di-pop");

        System.out.println(Utils.timeSpent(System.nanoTime() - startTime));
    }

    public static void updateData(Stack stack){
        Scanner inputObj = new Scanner(System.in);
        Scanner changeData = new Scanner(System.in);
        int size = stack.getSize();

        if(isDataEmpty(stack)) return;

        showData(stack);
        System.out.println("Pilih index data yang ingin diubah!");
        System.out.print("index=====: ");
        int changeIndex = inputObj.nextInt();

        if(changeIndex < 1 || changeIndex > size){
            System.out.println("!!! Error: Index out of bound !!!");
            return;
        }

        System.out.println("Masukkan data yang akan diubah");
        System.out.print("NewData===: ");
        String newData = changeData.nextLine();
        long startTime = System.nanoTime();

        ArrayList<Object> tempList = new ArrayList<>();
        while(stack.hasPop()) tempList.add(stack.pop());

        tempList.set(size - changeIndex, Utils.parseInput(newData));

        for(int i = tempList.size() - 1; i >= 0; i--){
            stack.push(tempList.get(i));
        }

        System.out.println("Data pada index " + changeIndex + " berhasil dibuah menjadi " + newData);
        System.out.println(Utils.timeSpent(System.nanoTime() - startTime));
    }
}
