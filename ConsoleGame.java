package ZadaniaWlasne.Zadania.tictactoe;

import ZadaniaWlasne.Zadania.tictactoe.player_move.PlayerMove;
import ZadaniaWlasne.Zadania.tictactoe.BackgroundApp.Player;
import ZadaniaWlasne.Zadania.tictactoe.BackgroundApp.TicTacToe;

import static ZadaniaWlasne.Zadania.tictactoe.BoardPrinter.printBoard;
import static ZadaniaWlasne.Zadania.tictactoe.Input.getInt;

/**
 * @author Tomasz Filo Zegarlicki
 * [https://github.com/FiloPL]
 * @date : 05.08.2019 22:37
 */
public class ConsoleGame {
    private TicTacToe game;

    private PlayerMove circlePlayer;
    private PlayerMove crossPlayer;


    public ConsoleGame(PlayerMove circlePlayer, PlayerMove crossPlayer){
        this.circlePlayer = circlePlayer;
        this.crossPlayer = crossPlayer;
    }


    public void start(){
        game = new TicTacToe();
        circlePlayer.setGame(game);
        crossPlayer.setGame(game);
        do {
            gameLoop();
        }
        while (!endGame());
    }


    private void gameLoop(){
        game.startNewGame();
        do {
            printBoard(game);
            int choice = getChoice();
            game.setMove(choice);
        }
        while (!game.isEndGame());
        printBoard(game);
    }


    private boolean endGame(){
        int choice;
        do {
            choice = getInt();
        }
        while (!(0 <= choice && choice <= 1));
        return choice == 0;
    }


    private int getChoice(){
        int choice;
        do {
            if (game.getCurrentPlayer() == Player.CIRCLE) {
                choice = circlePlayer.getMove();
            } else {
                choice = crossPlayer.getMove();
            }
        }
        while (!correctChoice(choice) || !game.isAvailableMove(choice));
        return choice;
    }


    private boolean correctChoice(int choice){
        return 0 <= choice && choice < 9;
    }
}
