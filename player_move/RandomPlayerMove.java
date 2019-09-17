package ZadaniaWlasne.Zadania.tictactoe.player_move;

import ZadaniaWlasne.Zadania.tictactoe.BackgroundApp.TicTacToe;

import java.util.Random;

/**
 * @author Tomasz Filo Zegarlicki
 * [https://github.com/FiloPL]
 * @date : 05.08.2019 22:44
 */
public class RandomPlayerMove implements PlayerMove {
    private TicTacToe game;

    @Override
    public int getMove(){
        int result;
        Random rnd = new Random();
        do {
            result = rnd.nextInt(9);
        }
        while (!game.isAvailableMove(result));

        return result;
    }

    @Override
    public void setGame(TicTacToe game){
        this.game = game;
    }
}
