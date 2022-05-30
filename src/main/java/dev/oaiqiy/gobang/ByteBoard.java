package dev.oaiqiy.gobang;

import lombok.Data;

@Data
public class ByteBoard implements Board {
    private final static int width = 15;
    private byte[][] boardArray;


    public ByteBoard(){
        boardArray = new byte[width][width];
    }

    public ByteBoard(Board board){
        this();

        for(int i = 0;i<width;i++)
            for(int j = 0;j<width;j++)
                boardArray[i][j] = (byte) board.getPawn(i,j);
    }

    public ByteBoard(byte[][] boardArray){
        this();

        for(int i= 0;i<width;i++)
            System.arraycopy(boardArray[i], 0, this.boardArray[i], 0, width);
    }



    @Override
    public int[][] nextPawns() {
        return new int[0][];
    }

    @Override
    public boolean isEmpty(int x, int y) {
        return boardArray[x][y] == 0;
    }

    @Override
    public boolean isEmpty(int[] location) {
        return isEmpty(location[0],location[1]);
    }

    @Override
    public Board dropWhite(int x, int y) {
        ByteBoard newBoard = new ByteBoard(boardArray);
        newBoard.boardArray[x][y] = 1;
        return newBoard;
    }

    @Override
    public Board dropWhite(int[] location) {
        return dropWhite(location[0],location[1]);
    }

    @Override
    public Board dropBlack(int x, int y) {
        ByteBoard newBoard = new ByteBoard(boardArray);
        newBoard.boardArray[x][y] = 2;
        return newBoard;
    }

    @Override
    public Board dropBlack(int[] location) {
        return dropBlack(location[0],location[1]);
    }

    @Override
    public int getPawn(int x, int y) {
        return boardArray[x][y];
    }

    @Override
    public int getPawn(int[] location) {
        return getPawn(location[0],location[1]);
    }

    @Override
    public int getWidth() {
        return width;
    }


}
