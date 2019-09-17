package ZadaniaWlasne.Zadania.tictactoe.BackgroundApp;

/**
 * @author Tomasz Filo Zegarlicki
 * [https://github.com/FiloPL]
 * @date : 05.08.2019 22:40
 */
public class TicTacToe {
    private final int[][] WIN_ROW = {
            {0, 1, 2}, {0, 4, 8}, {0, 3, 6}, {1, 4, 7},
            {2, 4, 6}, {2, 5, 8}, {3, 4, 5}, {6, 7, 8}};


    private Player[] board;
    private Player currentPlayer;
    private int games;
    private int circleWins;
    private int crossWins;
    private int draws;

    public TicTacToe() {
        board = new Player[9];
        games = 0;
        circleWins = 0;
        crossWins = 0;
        draws = 0;
        currentPlayer = Player.NONE;
    }


    public void startNewGame() {
        clearBoard();
        if (currentPlayer == Player.NONE)
            setNextPlayer();
        games++;
    }


    public Player getFieldStatus(int fieldNumber) {
        return board[fieldNumber];
    }


    public int getGamesCount() {
        return games;
    }


    public int getCircleWins() {
        return circleWins;
    }


    public int getCrossWins() {
        return crossWins;
    }


    public int getDraws() {
        return draws;
    }


    public Player getCurrentPlayer() {
        return currentPlayer;
    }


    public boolean isAvailableMove(int fieldNumber) {
        return getFieldStatus(fieldNumber) == Player.NONE;
    }


    public void setMove(int fieldNumber) {
        if (isAvailableMove(fieldNumber) && !isEndGame()) {
            board[fieldNumber] = currentPlayer;
            setNextPlayer();
        }
    }


    public boolean isEndGame() {
        boolean result = false;

        result |= isWin(Player.CIRCLE);
        result |= isWin(Player.CROSS);
        result |= isDraw();

        if (result)
            setScore();

        return result;
    }


    public Player whoWonGame() {
        if (isEndGame()) {
            if (isDraw())
                return Player.NONE;
            else if (isWin(Player.CROSS))
                return Player.CROSS;
            else if (isWin(Player.CIRCLE))
                return Player.CIRCLE;

        }
        return Player.NONE;
    }




    private void setScore() {
        if (isDraw())
            setPlayerScore(Player.NONE);
        if (isWin(Player.CIRCLE))
            setPlayerScore(Player.CIRCLE);
        if (isWin(Player.CROSS))
            setPlayerScore(Player.CROSS);
    }


    private void setNextPlayer() {
        if (currentPlayer != Player.CIRCLE)
            currentPlayer = Player.CIRCLE;
        else
            currentPlayer = Player.CROSS;
    }


    private void clearBoard() {
        for (int i = 0; i < board.length; i++)
            board[i] = Player.NONE;
    }


    private boolean isDraw() {
        for (int i = 0; i < 9; i++)
            if (isAvailableMove(i))
                return false;

        boolean playerWin = false;
        playerWin |= isWin(Player.CIRCLE);
        playerWin |= isWin(Player.CROSS);
        return !playerWin;
    }


    private boolean isWin(Player player) {
        for (int i = 0; i < WIN_ROW.length; i++)
            if (isRowWin(player, WIN_ROW[i]))
                return true;
        return false;
    }


    private void setPlayerScore(Player player) {
        if (getGamesCount() != getDraws() + getCircleWins() + getCrossWins()) {
            if (player == Player.NONE)
                draws++;
            if (player == Player.CROSS)
                crossWins++;
            if (player == Player.CIRCLE)
                circleWins++;
        }
    }


    private boolean isRowWin(Player player, int[] check) {
        boolean result = true;

        result &= board[check[0]] == player;
        result &= board[check[1]] == player;
        result &= board[check[2]] == player;

        return result;
    }
}
