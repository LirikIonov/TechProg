package hw1;

import java.util.Arrays;
import java.util.Scanner;

public class A4 {
    Scanner in = new Scanner(System.in);

    void solve1() {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            if (a[i] % 2 == 0) {
                System.out.println(a[i]);
            }
        }
        for (int i = 0; i < n; i++) {
            if (a[i] % 2 != 0) {
                System.out.println(a[i]);
            }
        }
    }

    void solve2() {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            if (a[i] % 3 == 0) {
                System.out.println(a[i]);
            }
        }
    }

    void solve3() {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            if (a[i] % 5 == 0) {
                System.out.println(a[i]);
            }
        }
    }

    void solve4() {
        int a = in.nextInt();
        int b = in.nextInt();
        int aa = a;
        int bb = b;
        while (b > 0) {
            a %= b;
            a ^= b;
            b ^= a;
            a ^= b;
        }
        System.out.println(a);
        System.out.println(aa / a * bb);
    }

    void solve5() {
        int n = in.nextInt();
        int[] a = new int[n];
        int mx = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            mx = Math.max(mx, a[i]);
        }
        boolean[] p = new boolean[mx + 2];
        Arrays.fill(p, true);
        p[0] = false;
        p[1] = false;
        for (int i = 2; i < mx + 2; i++) {
            for (int j = i * i; j < mx + 2; j += i) {
                p[j] = false;
            }
        }
        for (int i = 0; i < n; i++) {
            if (p[a[i]]) {
                System.out.println(i);
            }
        }
    }

    void precomputeLucky(int x, int cntr, boolean[] l) {
        if (cntr > x) {
            l[x] = true;
        }
        if (x % cntr == 0) {
            l[x] = false;
        }
        precomputeLucky(x - x / cntr, cntr + 1, l);
    }

    void solve6() {
        int n = in.nextInt();
        int[] a = new int[n];
        int mx = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            mx = Math.max(mx, a[i]);
        }
        boolean[] l = new boolean[mx + 1];
        Arrays.fill(l, true);
        l[0] = false;
        precomputeLucky(mx, 2, l);
        for (int i = 0; i < n; i++) {
            if (l[a[i]]) {
                System.out.println(i);
            }
        }
    }

    void solve7() {
        int number = in.nextInt();
        switch (number) {
            case 1:
                System.out.println("Один");
                break;
            case 2:
                System.out.println("Два");
                break;
            case 3:
                System.out.println("Три");
                break;
            case 4:
                System.out.println("Четыре");
                break;
            case 5:
                System.out.println("Пять");
                break;
            case 6:
                System.out.println("Шесть");
                break;
            case 7:
                System.out.println("Семь");
                break;
            case 8:
                System.out.println("Восемь");
                break;
            case 9:
                System.out.println("Девять");
                break;
            default:
                System.out.println("Введено некорректное число");
                break;
        }
    }

    void wrong() {
        System.out.println("Введите правильный номер задания.");
    }

    void run() {
        while (true) {
            System.out.println("Введите число от 1 до 7:");
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
                case 5:
                    solve5();
                    break;
                case 6:
                    solve6();
                    break;
                case 7:
                    solve7();
                    break;
                default:
                    wrong();
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new A4().run();
    }
}
