package dev.oaiqiy.gui;

import dev.oaiqiy.gobang.ByteBoard;
import dev.oaiqiy.gobang.Role;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import lombok.Data;

import java.util.function.Predicate;

@Data
public class BoardComponent extends Pane {
    public final static int width = ByteBoard.width;
    public final static int block = 30;
    public final static int offset = 15;
    private Line[] horizontal, vertical;
    private boolean playerRound = false;
    private int playerRole = Role.ROLE_WHITE;


    public BoardComponent(){
        horizontal = new Line[width];
        vertical = new Line[width];

        for(int i = 0;i<width;i++){

            horizontal[i] = new Line(offset + block * i, offset, offset + block *i, offset + (width - 1) * block);
            vertical[i] = new Line(offset, offset + block * i, offset + (width - 1) * block, offset + block * i);
        }

        getChildren().addAll(horizontal);
        getChildren().addAll(vertical);

        setWidth(offset * 2 + block * (width - 1));
        setHeight(offset * 2 + block * (width - 1));

        setOnMouseClicked(e ->{

            if(!playerRound)
                return;


            double x = e.getSceneX();
            double y = e.getSceneY();


            x -= offset;
            y -= offset;


            int locX = (int) Math.floor(x / block);
            int locY = (int) Math.floor(y / block);



            double offsetX = x % block;
            double offsetY = y % block;

            if(offsetX > block / 2.0)
                locX++;
            if(offsetY > block / 2.0)
                locY++;

            if(locX >= 0 && locX < width && locY >= 0 && locY < width){
                put(locX,locY,playerRole);
                App.playerQueue.add(new int[]{locX, locY});
                playerRound = false;
            }



        });
    }

    public void put(int x, int y, int role){
        PawnComponent pawn = new PawnComponent(role);
        pawn.setCenterX(offset + x * block);
        pawn.setCenterY(offset + y * block);
        getChildren().add(pawn);
    }

    public void clear(){
        getChildren().removeIf(node -> node.getClass() != Line.class);
    }

    public void setPlayerRole(int playerRole) {
        this.playerRole = playerRole;
        playerRound = playerRole != Role.ROLE_WHITE;
        clear();
        App.restart = true;
        App.playRole = playerRole;
    }
}
