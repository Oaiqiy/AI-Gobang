package dev.oaiqiy.gobang;


import java.util.Deque;

public interface Board {
    int[][] nextPawns();

    boolean isEmpty(int x, int y);
    boolean isEmpty(int[] location);

    Board dropWhite(int x, int y);
    Board dropWhite(int[] location);

    Board dropBlack(int x, int y);
    Board dropBlack(int[] location);

    Board dropPawn(int x, int y, int role);
    Board dropPawn(int[] location, int role);

    int getPawn(int x, int y);
    int getPawn(int[] location);

    int getWidth();

    Deque<int[]> generateNext();


}
