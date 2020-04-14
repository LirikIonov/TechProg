import utils.ConsoleInterpretator;

public class Bootstrap {
    public static void main(String[] args) {
        ConsoleInterpretator console = new ConsoleInterpretator();
        if (!console.setUp()) return;
        console.run();
    }
}