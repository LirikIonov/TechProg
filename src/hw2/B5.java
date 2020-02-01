package hw2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class B5 {
    Scanner in = new Scanner(System.in);

    void run() {
        int n = in.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            list.add(x);
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            System.out.println(list.contains(x) ? "YES" : "NO");
        }
    }

    public static void main(String[] args) {
        new B5().run();
    }
}
