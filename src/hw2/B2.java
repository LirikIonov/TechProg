package hw2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class B2 {
    Scanner in = new Scanner(System.in);

    void run() {
        int n = in.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            list.add(x);
        }
        System.out.println(list.parallelStream().reduce(0, Integer::sum));
    }

    public static void main(String[] args) {
        new B2().run();
    }
}
