import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inputObj = new Scanner(System.in);
        Stack stack = new Stack();

        while (true) {
            System.out.println("========= Pilih aksi =========");
            System.out.println("1. Show data");
            System.out.println("2. Push data");
            System.out.println("3. Pop data");
            System.out.println("4. Swap data");
            System.out.print("======: ");
            int choice = inputObj.nextInt();
            inputObj.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        showData(stack);
                        System.out.println("Enter untuk kembali ke menu");
                        inputObj.nextLine();
                        break;
                    case 2:
                        addData(stack, inputObj);
                        break;
                    case 3:
                        popData(stack);
                        break;
                    case 4:
                        updateData(stack, inputObj);
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void showData(Stack stack) {
        if (isDataEmpty(stack)) return;
        long startTime = System.nanoTime();

        Stack.Node current = stack.first;
        int count = stack.getSize();

        System.out.println("|-----------Data stack-----------|");
        while (current != null) {
            System.out.println(count + ". " + current.value + " ");
            current = current.next;
            count--;
        }
        System.out.println("|--------------------------------|");

        System.out.println(Utils.timeSpent(System.nanoTime() - startTime));
    }

    public static void addData(Stack stack, Scanner inputObj) {
        while (true) {
            System.out.println("Masukkan data (kosongkan untuk break): ");
            String data = inputObj.nextLine();

            if (data.isEmpty()) break;

            long startProgram = System.nanoTime();
            stack.push(Utils.parseInput(data));
            System.out.println(Utils.timeSpent(System.nanoTime() - startProgram));
        }
    }

    public static boolean isDataEmpty(Stack stack) {
        long startTime = System.nanoTime();
        if (!stack.hasPop()) System.out.println("!!!!! Data tidak tersedia !!!!!");
        System.out.println(Utils.timeSpent(System.nanoTime() - startTime));
        return !stack.hasPop();
    }

    public static void popData(Stack stack) {
        if (isDataEmpty(stack)) return;
        long startTime = System.nanoTime();

        stack.pop();
        System.out.println("Data berhasil di-pop");

        System.out.println(Utils.timeSpent(System.nanoTime() - startTime));
    }

    public static void updateData(Stack stack, Scanner inputObj) {
        int size = stack.getSize();

        if (isDataEmpty(stack)) return;

        showData(stack);
        System.out.println("Pilih index data yang ingin diubah!");
        System.out.print("index=====: ");
        int changeIndex = inputObj.nextInt();
        inputObj.nextLine(); // consume newline

        if (changeIndex < 1 || changeIndex > size) {
            System.out.println("!!! Error: Index out of bound !!!");
            return;
        }

        System.out.println("Masukkan data yang akan diubah");
        System.out.print("NewData===: ");
        String newData = inputObj.nextLine();

        long startTime = System.nanoTime();

        ArrayList<Object> tempList = new ArrayList<>();
        while (stack.hasPop()) tempList.add(stack.pop());

        tempList.set(size - changeIndex, Utils.parseInput(newData));

        for (int i = tempList.size() - 1; i >= 0; i--) {
            stack.push(tempList.get(i));
        }

        System.out.println("Data pada index " + changeIndex + " berhasil diubah menjadi " + newData);
        System.out.println(Utils.timeSpent(System.nanoTime() - startTime));
    }
}
