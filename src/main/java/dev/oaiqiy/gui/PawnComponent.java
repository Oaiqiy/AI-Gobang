package dev.oaiqiy.gui;

import dev.oaiqiy.gobang.Role;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PawnComponent extends Circle {


    public PawnComponent(int role){
        setRadius(BoardComponent.block / 3.0);
        setStroke(Color.BLACK);
        if(role == Role.ROLE_BLACK)
            setFill(Color.BLACK);
        else if(role == Role.ROLE_WHITE)
            setFill(Color.WHITE);
        else
            throw new RuntimeException();

    }


}
