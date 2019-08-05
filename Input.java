package ZadaniaWlasne.Zadania.tictactoe;

import java.util.Scanner;

/**
 * @author Tomasz Filo Zegarlicki
 * [https://github.com/FiloPL]
 * @date : 05.08.2019 22:38
 */
public class Input {
    public static int getInt(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(">");
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                return value;
            } else {
                System.out.println("invalid characters, try again");
            }
            scanner.nextLine();
        }
    }
}
