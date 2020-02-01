package hw2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class B1 {
    Scanner in = new Scanner(System.in);

    void run() {
        int n = in.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            list.add(x);
        }
        list.sort(Collections.reverseOrder());
        list.forEach(System.out::println);
    }

    public static void main(String[] args) {
        new B1().run();
    }
}
