package dev.oaiqiy.gobang;

import dev.oaiqiy.gobang.Board;

public interface Evaluation {

    /**
     * @param board the calculated board
     * @param role the role of player, false means white, true mean black.
     * @return the score of this role
     */
    int calculateBoardScore(Board board, int role);

    int calculatePawnScore(Board board, int x, int y, int role);
}
