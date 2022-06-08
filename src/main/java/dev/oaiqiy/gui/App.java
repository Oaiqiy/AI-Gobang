package dev.oaiqiy.gui;

import dev.oaiqiy.gobang.*;
import javafx.application.Platform;
import javafx.scene.control.Label;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class App {
    public static BlockingQueue<int[]> playerQueue = new ArrayBlockingQueue<>(1);


    public static void main(String[] args) throws InterruptedException {

        Board board = new ByteBoard();
        new Thread(() -> MainDisplay.main(args)).start();

        Thread.sleep(2000);

        AlphaBetaSearch search = new AlphaBetaSearch();
        TraversalEvaluation evaluation = new TraversalEvaluation();

        int computerRole = Role.ROLE_WHITE;

        if(computerRole == Role.ROLE_BLACK)
            board = uiPut(new int[]{board.getWidth()/2,board.getWidth()/2},computerRole,board);
        else{
            MainDisplay.boardComponent.setPlayerRole(Role.ROLE_BLACK);
            MainDisplay.boardComponent.setPlayerRound(true);
        }

        while (true){
            int[] next = playerQueue.take();
            board = board.dropPawn(next, Role.reverseRole(computerRole));

             int result = evaluation.judgeWinner(board);
             if(result != 0){
                 stopGame( result);
                 break;
             }


            int[] comNext = search.search(board, computerRole);
            log.info("computer step  " + comNext[0] +"  " + comNext[1]);

            board = uiPut(comNext, computerRole, board);

            result = evaluation.judgeWinner(board);
            if(result != 0){
                stopGame(result);
                break;
            }
        }


    }

    private static Board uiPut(int[] loc, int role, Board board){
        Platform.runLater(()-> {
            MainDisplay.boardComponent.put(loc[0], loc[1], role);
            MainDisplay.boardComponent.setPlayerRound(true);
        });

        return  board.dropPawn(loc, role);
    }

    private static void stopGame(int role){
        Platform.runLater(()->{
            MainDisplay.boardComponent.setPlayerRound(false);
            Label label = new Label();
            if(role == Role.ROLE_BLACK)
                label.setText("Black win");
            else
                label.setText("White win");
            MainDisplay.boardComponent.getChildren().add(label);
        });

    }




}
