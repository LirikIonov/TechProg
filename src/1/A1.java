import java.math.BigDecimal;
import java.util.Scanner;

public class A1 {
    Scanner in = new Scanner(System.in);

    void solve1() {
        int x = in.nextInt();
        int y = in.nextInt();
        long sumOfSqrs = x * x + y * y;
        long sqrSum = (x + y) * (x + y);
        System.out.println(Math.max(sumOfSqrs, sqrSum));
    }

    void solve2() {
        double salary = in.nextDouble();
        int experience = in.nextInt();
        int premium = 0;
        if (experience >= 2 && experience < 5) {
            salary *= 1.02;
            premium = 2;
        }
        else if (experience >= 5 && experience < 10) {
            salary *= 1.05;
            premium = 5;
        }
        System.out.println(premium + " " + salary);
    }

    void solve3() {
        double x0 = in.nextDouble();
        double y0 = in.nextDouble();
        double x1 = in.nextDouble();
        double y1 = in.nextDouble();
        double r1 = Math.sqrt(x0 * x0 + y0 * y0);
        double r2 = Math.sqrt(x1 * x1 + y1 * y1);
        System.out.println(Math.max(r1, r2));
    }

    void solve4() {
        BigDecimal a = new BigDecimal(in.next());
        BigDecimal b = new BigDecimal(in.next());
        BigDecimal c = new BigDecimal(in.next());
        System.out.println((a.equals(b.multiply(c)) ||
                b.equals(a.multiply(c)) ||
                c.equals(a.multiply(c))) ? "YES" : "NO");
    }

    void solve5() {
        int x1 = in.nextInt();
        int x2 = in.nextInt();
        int x3 = in.nextInt();
        System.out.println((Math.abs(x1) == x1 ? x1 * x1 : x1) + " " +
                (Math.abs(x2) == x2 ? x2 * x2 : x2) + " " +
                (Math.abs(x3) == x3 ? x3 * x3 : x3));
    }

    void solve6() {
        String type = in.next().toLowerCase();
        switch (type) {
            case "декабрь":
            case "январь":
            case "февраль":
                System.out.println("Зима");
                break;
            case "март":
            case "апрель":
            case "май":
                System.out.println("Весна");
                break;
            case "июнь":
            case "июль":
            case "август":
                System.out.println("Лето");
                break;
            case "сентябрь":
            case "октябрь":
            case "ноябрь":
                System.out.println("Осень");
                break;
            default:
                System.out.println("Введен некорректный месяц");
                break;
        }
    }

    void wrong() {
        System.out.println("Введите правильный номер задания.");
    }

    void run() {
        while (true) {
            System.out.println("Введите число от 1 до 6:");
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
                default:
                    wrong();
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new A1().run();
    }
}
