package ZadaniaWlasne.Zadania.tictactoe;

import ZadaniaWlasne.Zadania.tictactoe.spineApp.Player;
import ZadaniaWlasne.Zadania.tictactoe.spineApp.TicTacToe;

/**
 * @author Tomasz Filo Zegarlicki
 * [https://github.com/FiloPL]
 * @date : 05.08.2019 22:37
 */
public class BoardPrinter {
    public static void printBoard(TicTacToe game) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 100; i++)
            sb.append("\n");

        sb.append("\n")
                .append("     │     │     \n")
                .append("  %s  │  %s  │  %s  \n")
                .append("    0│    1│    2\n")
                .append("─────┼─────┼─────\n")
                .append("     │     │     \n")
                .append("  %s  │  %s  │  %s  \n")
                .append("    3│    4│    5\n")
                .append("─────┼─────┼─────\n")
                .append("     │     │     \n")
                .append("  %s  │  %s  │  %s  \n")
                .append("    6│    7│    8\n");


        String res = sb.toString();

        res = String.format(res,
                getState(game.getFieldStatus(0)),
                getState(game.getFieldStatus(1)),
                getState(game.getFieldStatus(2)),
                getState(game.getFieldStatus(3)),
                getState(game.getFieldStatus(4)),
                getState(game.getFieldStatus(5)),
                getState(game.getFieldStatus(6)),
                getState(game.getFieldStatus(7)),
                getState(game.getFieldStatus(8)));

        sb = new StringBuilder(res);
        sb.append("\n statystyka gry:")
                .append("\n\todbytych gier      - ")
                .append(game.getGamesCount())
                .append("\n\twygrane kółek      - ")
                .append(game.getCircleWins())
                .append("\n\twygrane krzyżyków  - ")
                .append(game.getCrossWins())
                .append("\n\tliczba remisów     - ")
                .append(game.getDraws());

        if (game.isEndGame()){
            gameEnd(sb, game);
        } else {
            gameNotEnd(sb, game);
        }

        System.out.println(sb);
    }




    private static void gameEnd(StringBuilder sb, TicTacToe game) {
        sb.append("\n\n");

        if (game.whoWonGame() == Player.NONE)
            sb.append("REMIS!!!");
        if (game.whoWonGame() == Player.CIRCLE)
            sb.append("KÓŁKO WYGRAŁO!");
        if (game.whoWonGame() == Player.CROSS)
            sb.append("KRZYŻYK WYGRAŁ!");

        sb.append("\n\nkolejna gra?\n\t1 - TAK :)\n\t0 - nie :(");
    }


    private static void gameNotEnd(StringBuilder sb, TicTacToe game){
        sb.append("\n\nruch gracza: ")
                .append(getState(game.getCurrentPlayer()))
                .append("\ndostępne pola ");

        for (int i = 0; i < 9; i++)
            if (game.isAvailableMove(i))
                sb.append(i).append(",");
    }


    private static String getState(Player fieldStatus) {
        switch (fieldStatus) {
            case NONE:
                return " ";
            case CIRCLE:
                return "O";
            case CROSS:
                return "X";
        }
        return null;
    }
}
