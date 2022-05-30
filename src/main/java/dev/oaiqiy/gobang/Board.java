package dev.oaiqiy.gobang;


public interface Board {
    int[][] nextPawns();

    boolean isEmpty(int x, int y);
    boolean isEmpty(int[] location);

    Board dropWhite(int x, int y);
    Board dropWhite(int[] location);

    Board dropBlack(int x, int y);
    Board dropBlack(int[] location);

    int getPawn(int x, int y);
    int getPawn(int[] location);

    int getWidth();


}
