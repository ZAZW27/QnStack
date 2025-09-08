import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inputObj = new Scanner(System.in);
        System.out.println("Pilih struktur data (1 = Stack, 2 = Queue): ");
        int choice = inputObj.nextInt();
        inputObj.nextLine();

        DataStructure ds;
        if (choice == 1) ds = new Stack();
        else ds = new Queue();

        while (true) {
            System.out.println("========= Pilih aksi =========");
            System.out.println("1. Show data");
            System.out.println("2. Push data");
            System.out.println("3. Pop data");
            System.out.println("4. Swap data");
            System.out.println("5. Keluar");
            System.out.print("======: ");
            String act = inputObj.nextLine();

            try {
                switch (act) {
                    case "1" -> showData(ds);
                    case "2" -> addData(ds, inputObj);
                    case "3" -> popData(ds);
                    case "4" -> {
                        if (ds instanceof Stack stack) {
                            swapData(stack, inputObj);
                        } else if (ds instanceof Queue queue) {
                            swapData(queue, inputObj);
                        } else {
                            System.out.println("Tidak ada struktur data yang terpilih");
                        }
                    }
                    case "5" -> {
                        System.out.println("Program selesai.");
                        inputObj.close();
                        return;
                    }
                    default -> System.out.println("Pilihan tidak valid!");
                }
            } catch (Exception e) {
                System.out.println("Terjadi error: " + e.getMessage());
            }
        }
    }

    public static void showData(DataStructure ds) {
        long startTime = System.nanoTime();

        if (!ds.hasPop()) {
            System.out.println("!!!!! Data tidak tersedia !!!!!");
            System.out.println(Utils.timeSpent(System.nanoTime() - startTime));
            return;
        }

        boolean isStack = ds instanceof Stack;

        System.out.println("|-----------Data-----------|");

        if (isStack) {
            // Stack: Tampilkan descending (index tinggi di atas)
            int count = ds.getSize() - 1;
            var current = ds.getFirstNode();
            while (current != null) {
                System.out.println(count + ". " + current.value);
                current = current.next;
                count--;
            }
        } else {
            // Queue: Tampilkan ascending (index rendah di atas)
            int count = 0;
            var current = ds.getFirstNode();
            while (current != null) {
                System.out.println(count + ". " + current.value);
                current = current.next;
                count++;
            }
        }

        System.out.println("|---------------------------|");
        System.out.println(Utils.timeSpent(System.nanoTime() - startTime));

        System.out.print("Tekan Enter untuk melanjutkan...");
        new Scanner(System.in).nextLine();
    }

    public static void addData(DataStructure ds, Scanner inputObj) {
        while (true) {
            System.out.print("Masukkan data (kosongkan untuk break): ");
            String data = inputObj.nextLine();
            if (data.isEmpty()) break;

            long startTime = System.nanoTime();
            // Parse input menggunakan Utils
            Object parsedData = Utils.parseInput(data);
            ds.push(parsedData);
            System.out.println(Utils.timeSpent(System.nanoTime() - startTime));
        }
    }

    public static void popData(DataStructure ds) {
        long startTime = System.nanoTime();

        if (!ds.hasPop()) {
            System.out.println("!!!!! Data tidak tersedia !!!!!");
            System.out.println(Utils.timeSpent(System.nanoTime() - startTime));
            return;
        }

        Object removed = ds.pop();
        System.out.println("Data berhasil di-pop: " + removed);
        System.out.println(Utils.timeSpent(System.nanoTime() - startTime));

        System.out.print("Tekan Enter untuk melanjutkan...");
        new Scanner(System.in).nextLine();
    }

    public static void swapData(Stack stack, Scanner inputObj) {
        long startTime = System.nanoTime();

        if (stack.getSize() < 2) {
            System.out.println("!!!!! Minimal harus ada 2 data untuk swap !!!!!");
            System.out.println(Utils.timeSpent(System.nanoTime() - startTime));
            return;
        }

        try {
            System.out.print("Pilih index-1 (0-" + (stack.getSize()-1) + "): ");
            int index1 = inputObj.nextInt();
            System.out.print("Pilih index-2 (0-" + (stack.getSize()-1) + "): ");
            int index2 = inputObj.nextInt();
            inputObj.nextLine(); // consume newline

            stack.swap(index1, index2);
            System.out.println(Utils.timeSpent(System.nanoTime() - startTime));
        } catch (Exception e) {
            System.out.println("Input tidak valid: " + e.getMessage());
            inputObj.nextLine(); // clear invalid input
        }

        System.out.print("Tekan Enter untuk melanjutkan...");
        inputObj.nextLine();
    }

    public static void swapData(Queue queue, Scanner inputObj) {
        long startTime = System.nanoTime();

        if (queue.getSize() < 2) {
            System.out.println("!!!!! Minimal harus ada 2 data untuk swap !!!!!");
            System.out.println(Utils.timeSpent(System.nanoTime() - startTime));
            return;
        }

        try {
            System.out.print("Pilih index-1 (0-" + (queue.getSize()-1) + "): ");
            int index1 = inputObj.nextInt();
            System.out.print("Pilih index-2 (0-" + (queue.getSize()-1) + "): ");
            int index2 = inputObj.nextInt();
            inputObj.nextLine(); // consume newline

            queue.swap(index1, index2);
            System.out.println(Utils.timeSpent(System.nanoTime() - startTime));
        } catch (Exception e) {
            System.out.println("Input tidak valid: " + e.getMessage());
            inputObj.nextLine(); // clear invalid input
        }

        System.out.print("Tekan Enter untuk melanjutkan...");
        inputObj.nextLine();
    }
}