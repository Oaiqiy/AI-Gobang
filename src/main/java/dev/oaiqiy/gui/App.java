package dev.oaiqiy.gui;

import dev.oaiqiy.gobang.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class App {
    public static BlockingQueue<int[]> playerQueue = new ArrayBlockingQueue<>(1);
    public static volatile boolean restart = false;
    public static volatile int playRole = Role.ROLE_WHITE;


    public static void main(String[] args) throws InterruptedException, ExecutionException {


        new Thread(() -> MainDisplay.main(args)).start();

        Thread.sleep(2000);

        AlphaBetaSearch search = new AlphaBetaSearch();
        TraversalEvaluation evaluation = new TraversalEvaluation();

        while(true){

            log.info("new game");
            Board board = new ByteBoard();
            int computerRole = Role.reverseRole(playRole);

            if(computerRole == Role.ROLE_BLACK)
                board = uiPut(new int[]{board.getWidth()/2,board.getWidth()/2},computerRole,board);
            restart = false;

            Game:
            while (true){
                while(playerQueue.peek() == null){
                    if(restart)
                        break Game;
                    Thread.sleep(100);
                }

                Task<int[]> task = new Task<>() {
                    @Override
                    protected int[] call() throws Exception {
                        return playerQueue.take();
                    }
                };

                new Thread(task).start();

                while(task.isRunning()){
                    if(restart)
                        break Game;
                    Thread.sleep(100);
                }

                int[] next = task.get();

                board = board.dropPawn(next, Role.reverseRole(computerRole));

                int result = evaluation.judgeWinner(board);
                if(result != 0){
                    stopGame(result);
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
