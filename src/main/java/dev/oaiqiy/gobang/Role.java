package dev.oaiqiy.gobang;

public class Role {

    public final static int ROLE_EMPTY = 0;
    public final static int ROLE_WHITE = 1;
    public final static int ROLE_BLACK = 2;

    public static int reverseRole(int role){
        if(role == ROLE_EMPTY)
            return ROLE_EMPTY;
        else if(role == ROLE_BLACK)
            return ROLE_WHITE;
        else
            return ROLE_BLACK;
    }
}
