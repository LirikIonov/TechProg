package hw2;

import java.util.*;
import java.util.stream.Collectors;

public class B6 {
    Scanner in = new Scanner(System.in);

    void run() {
        int n = in.nextInt();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            map.put(i, x);
        }
        Map<Integer, Integer> sortedMap = map.entrySet().parallelStream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        sortedMap.forEach((k, v) -> System.out.println(k + " " + v));
    }

    public static void main(String[] args) {
        new B6().run();
    }
}
