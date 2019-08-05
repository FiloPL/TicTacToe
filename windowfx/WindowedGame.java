package ZadaniaWlasne.Zadania.tictactoe.windowfx;

import ZadaniaWlasne.Zadania.tictactoe.player_move.HumanPlayerMove;
import ZadaniaWlasne.Zadania.tictactoe.player_move.PlayerMove;
import ZadaniaWlasne.Zadania.tictactoe.spineApp.Player;
import ZadaniaWlasne.Zadania.tictactoe.spineApp.TicTacToe;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * @author Tomasz Filo Zegarlicki
 * [https://github.com/FiloPL]
 * @date : 05.08.2019 22:48
 */
public class WindowedGame implements Refresh {
    private PlayerMove circlePlayer;
    private PlayerMove crossPlayer;


    public WindowedGame(Stage primaryStage, PlayerMove circlePlayer, PlayerMove crossPlayer){

        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        primaryStage.setTitle("Kółko i krzyżyk - ver. " + VERSION);

        this.circlePlayer = circlePlayer;
        this.crossPlayer = crossPlayer;
    }


    private final String VERSION = "0.1";
    private Stage primaryStage;
    private TicTacToe game;
    private Button btnNewGame;
    private Label lblGameCount;
    private Label lblCircleWinCount;
    private Label lblCrossWinCount;
    private Label lblDraw;
    private Label lblCurrentMove;
    private GridPane board;


    public void show(){
        game = new TicTacToe();
        crossPlayer.setGame(game);
        circlePlayer.setGame(game);

        prepareGame();

        game.startNewGame();
        refreshControls();


        primaryStage.show();
    }


    private void prepareGame(){

        HBox back = new HBox();

        back.getChildren().add(getSpace());
        back.getChildren().add(getGameBoard());
        back.getChildren().add(getSpace());
        back.getChildren().add(getGameScoreTable());

        Scene scene = new Scene(back, 420, 198);
        primaryStage.setScene(scene);
    }


    @Override
    public void refreshControls(){
        btnNewGame.setDisable(!game.isEndGame());
        lblGameCount.setText(Integer.toString(game.getGamesCount()));
        lblCircleWinCount.setText(Integer.toString(game.getCircleWins()));
        lblCrossWinCount.setText(Integer.toString(game.getCrossWins()));
        lblDraw.setText(Integer.toString(game.getDraws()));

        String text = "";

        if (game.isEndGame()) {

            switch (game.whoWonGame()) {
                case NONE:
                    text = "REMIS";
                    break;
                case CIRCLE:
                    text = "wygrało KÓŁKO";
                    break;
                case CROSS:
                    text = "wygrał KRZYŻYK";
            }
        } else {
            text = "ruch dla ";
            switch (game.getCurrentPlayer()) {
                case CIRCLE:
                    text += "KÓŁKO";
                    break;
                case CROSS:
                    text += "KRZYŻYK";
            }
        }
        lblCurrentMove.setText(text);

        for (Node node : board.getChildren()) {
            TicTacToeButton btn = (TicTacToeButton) node;
            btn.setState();
        }

        executeComputerMove();
    }


    private void executeComputerMove(){

        if (!game.isEndGame()) {
            if (game.getCurrentPlayer() == Player.CIRCLE && !(circlePlayer instanceof HumanPlayerMove)) {
                int choice = circlePlayer.getMove();
                for (Node node : board.getChildren()) {
                    TicTacToeButton btn = (TicTacToeButton) node;
                    btn.tryExecute(choice);
                }
            } else {
                if (game.getCurrentPlayer() == Player.CROSS && !(crossPlayer instanceof HumanPlayerMove)) {
                    int choice = crossPlayer.getMove();
                    for (Node node : board.getChildren()) {
                        TicTacToeButton btn = (TicTacToeButton) node;
                        btn.tryExecute(choice);
                    }
                }
            }
        }
    }


    private Pane getGameBoard(){
        StackPane gameBoard = new StackPane();
        gameBoard.setPrefSize(198, 198);

        board = new GridPane();
        board.setHgap(8);
        board.setVgap(8);

        for (int i = 0; i < 9; i++)
            addBoardButton(board, i);

        gameBoard.getChildren().add(board);
        board.setAlignment(Pos.CENTER);

        return gameBoard;
    }


    private Pane getGameScoreTable(){
        VBox container = new VBox();
        container.getChildren().add(getSpace());

        //new game button
        btnNewGame = new Button();
        btnNewGame.setText("nowa gra");
        btnNewGame.setPrefWidth(198);
        btnNewGame.setPrefHeight(25);
        Font font = Font.font(btnNewGame.getFont().getFamily(), FontWeight.BOLD, btnNewGame.getFont().getSize());
        btnNewGame.setFont(font);
        btnNewGame.setOnMouseClicked(event -> {
            game.startNewGame();
            refreshControls();
        });
        container.getChildren().add(btnNewGame);
        container.getChildren().add(getSpace());

        //label stats
        Label lblStats = new Label("statystyki gry");
        lblStats.setFont(font);
        container.getChildren().add(lblStats);

        //game count
        lblGameCount = getScoreLineLabel(container, "liczba gier: ");
        lblCircleWinCount = getScoreLineLabel(container, "wygrane kółek: ");
        lblCrossWinCount = getScoreLineLabel(container, "lwygrane krzyżyków: ");
        lblDraw = getScoreLineLabel(container, "liczba remisów: ");

        //space
        container.getChildren().add(getSpace());

        //label current move or indicate winner
        Label lblMove = new Label("obecny ruch / wygrana");
        container.getChildren().add(lblMove);

        lblCurrentMove = getBoldLabel();
        lblCurrentMove.setMinWidth(198);
        container.getChildren().add(lblCurrentMove);

        return container;
    }


    private void addBoardButton(GridPane board, int i){
        TicTacToeButton btn = new TicTacToeButton(i, game, this);
        btn.setMinSize(50, 50);
        board.add(btn, i % 3, i / 3);
    }


    private BorderPane getSpace(){
        BorderPane borderPane = new BorderPane();
        borderPane.setMinSize(8, 8);
        return borderPane;
    }


    private Label getScoreLineLabel(Pane container, String caption){
        HBox box = new HBox();
        box.getChildren().add(getSpace());

        Label lblCaption = new Label(caption);
        lblCaption.setPrefWidth(150);
        box.getChildren().add(lblCaption);

        Label lblCounter = getBoldLabel();
        box.getChildren().add(lblCounter);
        container.getChildren().add(box);

        return lblCounter;
    }


    private Font getBoldFont(){
        return Font.font("", FontWeight.BOLD, 12);
    }


    private Label getBoldLabel(){
        Label label = new Label("-");
        label.setPrefWidth(40);
        label.setTextAlignment(TextAlignment.RIGHT);
        label.setFont(getBoldFont());
        return label;
    }
}
