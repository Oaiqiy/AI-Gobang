package dev.oaiqiy.gobang;

public class TraversalEvaluation{
    private final static int radius = 8;
    private final static int[][] dirs = new int[][]{{1,0},{0,1},{1,1},{1,-1}};


    public int calculateBoardScore(Board board, int role) {
        final int width = board.getWidth();
        int roleScore = 0,competitorScore = 0;

        for(int i = 0;i<width;i++)
            for(int j = 0;j<width;j++){
               roleScore += calculatePawnScore(board,i,j,role);
               competitorScore += calculatePawnScore(board,i,j,Role.reverseRole(role));
            }

        return roleScore - competitorScore;
    }


    public int calculatePawnScore(Board board, int x, int y, int role) {
        if(board.getPawn(x,y) != role)
            return 0;

        final int width = board.getWidth();

        int sum = 0;

        for(var dir: dirs){
            int count = 1, block = 0,empty = -1;

            for(int i = x + dir[0],j=y+dir[1];true;i+= dir[0],j+=dir[1]){
                if(!checkBorder(i,j,width)){
                    block++;
                    break;
                }

                int pawn = board.getPawn(i,j);

                if(pawn == Role.ROLE_EMPTY){
                    if(empty == -1 && checkBorder(i + dir[0],j + dir[1] ,width) && board.getPawn(i + dir[0], j + dir[1]) == role){
                        empty = count;
                    }
                    else{
                        break;
                    }
                }else if(pawn == role){
                    count++;
                }else{
                    block++;
                    break;
                }

            }


            for(int i = x - dir[0], j = y - dir[1];true;i -= dir[0],j-= dir[1]){
                if(!checkBorder(i,j,width)){
                    block++;
                    break;
                }

                int pawn = board.getPawn(i,j);

                if(pawn == Role.ROLE_EMPTY){
                    if(empty == -1 && checkBorder(i-dir[0] ,j-dir[1],width) && board.getPawn(i-dir[0],j-dir[1]) == role){
                        empty = 0;
                    }else{
                        break;
                    }

                }else if(pawn == role){
                    count++;
                    if(empty != -1)
                        empty++;
                }else{
                    block ++;
                    break;
                }


            }

            sum += countToScore(count,block,empty);

        }



        return sum;
    }

    public int judgeWinner(Board board){
        for(int i = 0;i<board.getWidth();i++)
            for(int j = 0;j<board.getWidth();j++){
                if(calculatePawnScore(board, i,j,Role.ROLE_WHITE) >= Score.FIVE)
                    return Role.ROLE_WHITE;
                else if(calculatePawnScore(board, i, j,Role.ROLE_BLACK) >= Score.FIVE)
                    return Role.ROLE_BLACK;
            }

        return 0;
    }

    private boolean checkBorder(int x, int y, int width){
        return x >= 0 && y >= 0 && x < width && y < width;
    }

    private int countToScore(int count, int block,int empty){
        if(empty <= 0) {
            if(count >= 5) return Score.FIVE;
            if(block == 0) {
                switch(count) {
                    case 1: return Score.ONE;
                    case 2: return Score.TWO;
                    case 3: return Score.THREE;
                    case 4: return Score.FOUR;
                }
            }

            if(block == 1) {
                switch(count) {
                    case 1: return Score.BLOCKED_ONE;
                    case 2: return Score.BLOCKED_TWO;
                    case 3: return Score.BLOCKED_THREE;
                    case 4: return Score.BLOCKED_FOUR;
                }
            }

        } else if(empty == 1 || empty == count-1) {
            if(count >= 6) {
                return Score.FIVE;
            }
            if(block == 0) {
                switch(count) {
                    case 2: return Score.TWO/2;
                    case 3: return Score.THREE;
                    case 4: return Score.BLOCKED_FOUR;
                    case 5: return Score.FOUR;
                }
            }

            if(block == 1) {
                switch(count) {
                    case 2: return Score.BLOCKED_TWO;
                    case 3: return Score.BLOCKED_THREE;
                    case 4:
                    case 5:
                        return Score.BLOCKED_FOUR;
                }
            }
        } else if(empty == 2 || empty == count-2) {
            if(count >= 7) {
                return Score.FIVE;
            }
            if(block == 0) {
                switch(count) {
                    case 3: return Score.THREE;
                    case 4:
                    case 5: return Score.BLOCKED_FOUR;
                    case 6: return Score.FOUR;
                }
            }

            if(block == 1) {
                switch(count) {
                    case 3: return Score.BLOCKED_THREE;
                    case 4:
                    case 5:
                        return Score.BLOCKED_FOUR;
                    case 6: return Score.FOUR;
                }
            }

            if(block == 2) {
                switch(count) {
                    case 4:
                    case 5:
                    case 6: return Score.BLOCKED_FOUR;
                }
            }
        } else if(empty == 3 || empty == count-3) {
            if(count >= 8) {
                return Score.FIVE;
            }
            if(block == 0) {
                switch(count) {
                    case 4:
                    case 5: return Score.THREE;
                    case 6: return Score.BLOCKED_FOUR;
                    case 7: return Score.FOUR;
                }
            }

            if(block == 1) {
                switch(count) {
                    case 4:
                    case 5:
                    case 6: return Score.BLOCKED_FOUR;
                    case 7: return Score.FOUR;
                }
            }

            if(block == 2) {
                switch(count) {
                    case 4:
                    case 5:
                    case 6:
                    case 7: return Score.BLOCKED_FOUR;
                }
            }
        } else if(empty == 4 || empty == count-4) {
            if(count >= 9) {
                return Score.FIVE;
            }
            if(block == 0) {
                switch(count) {
                    case 5:
                    case 6:
                    case 7:
                    case 8: return Score.FOUR;
                }
            }

            if(block == 1) {
                switch(count) {
                    case 4:
                    case 5:
                    case 6:
                    case 7: return Score.BLOCKED_FOUR;
                    case 8: return Score.FOUR;
                }
            }

            if(block == 2) {
                switch(count) {
                    case 5:
                    case 6:
                    case 7:
                    case 8: return Score.BLOCKED_FOUR;
                }
            }
        } else if(empty == 5 || empty == count-5) {
            return Score.FIVE;
        }

        return 0;
    }





}
