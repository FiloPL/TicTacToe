package ZadaniaWlasne.Zadania.tictactoe.player_move;

import ZadaniaWlasne.Zadania.tictactoe.BackgroundApp.TicTacToe;

/**
 * @author Tomasz Filo Zegarlicki
 * [https://github.com/FiloPL]
 * @date : 05.08.2019 22:41
 */
public interface PlayerMove {

    int getMove();
    void setGame(TicTacToe game);
}
