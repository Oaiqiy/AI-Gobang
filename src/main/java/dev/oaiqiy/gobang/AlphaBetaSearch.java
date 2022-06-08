package dev.oaiqiy.gobang;

import lombok.extern.slf4j.Slf4j;

import java.util.Deque;
@Slf4j
public class AlphaBetaSearch {

    private final int depth = 3;
    private final TraversalEvaluation traversalEvaluation = new TraversalEvaluation();
    private int[] step = new int[2];

    public int[] search(Board board, int role){
        maxLevel(board, role, Integer.MIN_VALUE, Integer.MAX_VALUE,1);
        return step;
    }


    private int minLevel(Board board, int role, int alpha, int beta, int depth){
        log.info("depth: " + depth);

        Deque<int[]> next = board.generateNext();


        for(var nextPawn : next){


            int score = maxLevel(board.dropPawn(nextPawn, Role.reverseRole(role)), role , alpha , beta, depth+1);
            log.info(nextPawn[0] + "  " + nextPawn[1] + " score : " + score);
            if(score < alpha)
                return score;

            if(beta > score){
                beta = score;

            }


        }

        return beta;


    }

    private int maxLevel(Board board,int role, int alpha, int beta, int depth){
        log.info("depth: " + depth);
        if(depth == this.depth){
            int s = traversalEvaluation.calculateBoardScore(board, role);
            System.out.println(s);
            return s;
        }

        Deque<int[]> next = board.generateNext();


        for(var nextPawn : next){
            int score = minLevel(board.dropPawn(nextPawn, role), role, alpha, beta, depth+1);

            log.info(nextPawn[0] + "  " + nextPawn[1] + " score : " + score);
            if(score > beta)
                return beta;

            if(alpha < score){
                log.info("max step " + nextPawn[0] + "  " + nextPawn[1]);
                if(depth == 1)
                    step = nextPawn;
                alpha = score;
            }

        }

        return alpha;

    }




}
