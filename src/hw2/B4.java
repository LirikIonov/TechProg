package hw2;

import java.util.LinkedList;
import java.util.Scanner;

public class B4 {
    Scanner in = new Scanner(System.in);

    void run() {
        int n = in.nextInt();
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            if (i % 2 == 0) {
                list.addFirst(x);
            }
            else {
                list.addLast(x);
            }
        }
        list.forEach(System.out::println);
    }

    public static void main(String[] args) {
        new B4().run();
    }
}
