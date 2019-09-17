package ZadaniaWlasne.Zadania.tictactoe.player_move;

import ZadaniaWlasne.Zadania.tictactoe.BackgroundApp.Player;
import ZadaniaWlasne.Zadania.tictactoe.BackgroundApp.TicTacToe;

import java.util.Random;

/**
 * @author Tomasz Filo Zegarlicki
 * [https://github.com/FiloPL]
 * @date : 05.08.2019 22:42
 */
public class AIPlayerMove implements PlayerMove {
    private TicTacToe game;
    private final int[][] WIN_ROW = {{0, 1, 2}, {0, 4, 8}, {0, 3, 6}, {1, 4, 7}, {2, 4, 6}, {2, 5, 8}, {3, 4, 5}, {6, 7, 8}};


    @Override
    public int getMove(){
        int choice;

        choice = getWinMove(game.getCurrentPlayer());

        if (choice < 0) {
            choice = getDrawMove();
        }

        return choice;
    }



    private int getDrawMove(){

        int choice;

        if (game.getCurrentPlayer() == Player.CIRCLE) {
            choice = getWinMove(Player.CROSS);
        } else {
            choice = getWinMove(Player.CIRCLE);
        }

        if (choice < 0) {
            choice = getRandomMove();
        }

        return choice;
    }


    private int getWinMove(Player player){
        int choice = -1;

        for (int i = 0; i < 8; i++) {

            int[] check = WIN_ROW[i];

            int cnt = 0;

            for (int j = 0; j < 3; j++) {
                if (game.getFieldStatus(check[j]) == player) {
                    cnt++;
                }
            }

            if (cnt == 2) {
                for (int j = 0; j < 3; j++) {
                    if (game.getFieldStatus(check[j]) == Player.NONE) {
                        return check[j];
                    }
                }
            }
        }
        return choice;
    }


    @Override
    public void setGame(TicTacToe game){
        this.game = game;
    }


    private int getRandomMove(){
        int result;
        Random rnd = new Random();
        do {
            result = rnd.nextInt(9);
        }
        while (!game.isAvailableMove(result));

        return result;
    }
}
