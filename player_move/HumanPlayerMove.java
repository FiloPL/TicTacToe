package ZadaniaWlasne.Zadania.tictactoe.player_move;

import ZadaniaWlasne.Zadania.tictactoe.spineApp.TicTacToe;

import static ZadaniaWlasne.Zadania.tictactoe.Input.getInt;

/**
 * @author Tomasz Filo Zegarlicki
 * [https://github.com/FiloPL]
 * @date : 05.08.2019 22:45
 */
public class HumanPlayerMove implements PlayerMove {
    private TicTacToe game;


    @Override
    public int getMove(){
        return getChoice();
    }


    @Override
    public void setGame(TicTacToe game){
        this.game = game;
    }


    private int getChoice(){
        int choice;
        do {
            choice = getInt();
        } while(!correctChoice(choice) || !game.isAvailableMove(choice));
        return choice;
    }

    private boolean correctChoice(int choice) {
        return 0<=choice && choice<9;
    }

}
