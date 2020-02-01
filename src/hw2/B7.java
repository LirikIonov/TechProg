package hw2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class B7 {
    Scanner in = new Scanner(System.in);
    Map<String, String> clients = new HashMap<>();

    boolean add(String key, String value) {
        if (!clients.containsKey(key)) {
            clients.put(key, value);
            return true;
        }
        return false;
    }

    boolean delete(String key) {
        if (clients.containsKey(key)) {
            clients.remove(key);
            return true;
        }
        return false;
    }

    boolean update(String key, String value) {
        if (clients.containsKey(key)) {
            clients.put(key, value);
            return true;
        }
        return false;
    }

    void reject(String ex){
        System.out.println(ex);
    }

    void run() {
        System.out.println("Usage:");
        System.out.println("ADD <clientNumber> <info>");
        System.out.println("DELETE <clientNumber>");
        System.out.println("UPDATE <clientNumber> <info>");
        while(true) {
            String input = in.nextLine();
            if (input.contains(" ")) {
                String type = input.substring(0, input.indexOf(" ")).toLowerCase();
                String payload = input.substring(input.indexOf(" ") + 1);
                if (type.equals("add")) {
                    if (payload.contains(" ")) {
                        String key = payload.substring(0, payload.indexOf(" "));
                        String value = payload.substring(payload.indexOf(" ") + 1);
                        if (!add(key, value)) {
                            reject("There's already client with that phone number");
                        }
                    }
                    else {
                        reject("Can't parse query");
                    }
                }
                else if (type.equals("delete")) {
                    if (!delete(payload)) {
                        reject("There's no client with that phone number");
                    }
                }
                else if (type.equals("update")) {
                    if (payload.contains(" ")) {
                        String key = payload.substring(0, payload.indexOf(" "));
                        String value = payload.substring(payload.indexOf(" ") + 1);
                        if (!update(key, value)) {
                            reject("There's no client with that phone number");
                        }
                    }
                    else {
                        reject("Can't parse query");
                    }
                }
                else {
                    reject("Can't parse query");
                }
            }
            else {
                reject("Can't parse query");
            }
        }
    }

    public static void main(String[] args) {
        new B7().run();
    }
}
