package dev.oaiqiy.gobang;

import lombok.Data;

import java.util.ArrayDeque;
import java.util.Deque;

@Data
public class ByteBoard implements Board {
    public final static int width = 15;
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
    public Board dropPawn(int x, int y, int role) {
        if(role == Role.ROLE_WHITE)
            return dropWhite(x, y);
        else if(role == Role.ROLE_BLACK)
            return dropBlack(x ,y);
        else
            throw new RuntimeException("cannot drop empty pawn");
    }

    @Override
    public Board dropPawn(int[] location , int role) {
        return dropPawn(location[0], location[1], role);
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

    @Override
    public Deque<int[]> generateNext() {
        Deque<int[]> ans = new ArrayDeque<>();

//        int width = 5;

        for(int i = 0;i<=(width - 1)/2;i++){
            for(int j= i;j<width-i;j++)
                if(boardArray[i][j] == 0)
                    ans.addFirst(new int[]{i,j});

            for(int j = i + 1;j<width-i;j++)
                if(boardArray[j][width - i - 1] == 0)
                    ans.addFirst(new int[]{j,width-i-1});

            for(int j = width - 2 - i;j >= i;j--)
                if(boardArray[width-i-1][j] == 0)
                    ans.addFirst(new int[]{width-i-1,j});

            for(int j = width - 2 -i;j > i;j--)
                if(boardArray[j][i] == 0)
                    ans.addFirst(new int[]{j,i});
        }
//        for(int[] a : ans){
//            System.out.println(a[0] + "  " + a[1]);
//        }
        return ans;
    }






}
