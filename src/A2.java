import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class A2 {
    Scanner in = new Scanner(System.in);

    void solve1() {
        int n = in.nextInt();
        List<Integer> group = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            if (x % 5 != 0 && x % 3 == 0) {
                int sum = 0;
                int rem = x;
                while (rem != 0) {
                    sum += rem % 10;
                    rem /= 10;
                }
                if (sum % 5 != 0 && sum % 3 == 0) {
                    group.add(x);
                }
            }
        }
        group.forEach(System.out::println);
    }

    void solve2() {
        int n = in.nextInt();
        for (int j = 5; j < n; j += 5) {
            System.out.println(j);
        }
    }

    void solve3() {
        int n = in.nextInt();
        System.out.println((n != 0 && (n & (n - 1)) == 0) ? "YES" : "NO");
    }

    void solve4() {
        int n = in.nextInt();
        int[] f = new int[n + 2];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i < n + 2; i++) {
            f[i] = f[i - 1] + f[i - 2];
            System.out.println(f[i]);
        }
    }

    void wrong() {
        System.out.println("Введите правильный номер задания.");
    }

    void run() {
        while (true) {
            System.out.println("Введите число от 1 до 4:");
            int type = 0;
            String input = in.next();
            try {
                type = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            switch (type) {
                case 1:
                    solve1();
                    break;
                case 2:
                    solve2();
                    break;
                case 3:
                    solve3();
                    break;
                case 4:
                    solve4();
                    break;
                default:
                    wrong();
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new A2().run();
    }
}
