package hw2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class B3 {
    Scanner in = new Scanner(System.in);

    void run() {
        int n = in.nextInt();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            map.put(x, i);
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            System.out.println(map.containsKey(x) ? "YES" : "NO");
        }
    }

    public static void main(String[] args) {
        new B3().run();
    }
}
