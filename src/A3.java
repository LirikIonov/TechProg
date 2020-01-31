import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class A3 {
    Scanner in = new Scanner(System.in);

    void solve1() {
        int a = in.nextInt();
        int b = in.nextInt();
        List<Integer> group = new ArrayList<>();
        for (int i = a; i < b + 1; i++) {
            group.add(i);
        }
        System.out.println(group.size());
        group.forEach(System.out::println);
    }

    void solve2() {
        int a = in.nextInt();
        int b = in.nextInt();
        List<Integer> group = new ArrayList<>();
        for (int i = b - 1; i > a; i--) {
            group.add(i);
        }
        System.out.println(group.size());
        group.forEach(System.out::println);
    }

    void solve3() {
        BigDecimal a = new BigDecimal(in.next());
        int n = in.nextInt();
        System.out.println(a.pow(n));
    }

    void solve4() {
        BigDecimal a = new BigDecimal(in.next());
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println(a.pow(n).intValue());
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
        new A3().run();
    }
}
