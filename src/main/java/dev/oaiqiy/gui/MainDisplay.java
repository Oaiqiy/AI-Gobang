package dev.oaiqiy.gui;

import dev.oaiqiy.gobang.Role;
import dev.oaiqiy.gobang.Score;
import javafx.application.Application;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import lombok.Data;

@Data
public class MainDisplay extends Application {

    public static BoardComponent boardComponent;

    @Override
    public void start(Stage stage) throws Exception {

        boardComponent = new BoardComponent();

        VBox menu = new VBox();
        Button black = new Button("黑子");
        black.setOnMouseClicked(e->{
            boardComponent.setPlayerRole(Role.ROLE_BLACK);
        });

        Button white = new Button("白子");
        white.setOnMouseClicked(e -> boardComponent.setPlayerRole(Role.ROLE_WHITE));
        menu.setSpacing(10);
        menu.setNodeOrientation(NodeOrientation.INHERIT);

        menu.getChildren().addAll(black, white);

        HBox all = new HBox();
        all.getChildren().addAll(boardComponent, menu);
        all.setSpacing(10);

        Scene scene = new Scene(all, 450, 450);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getId());
        launch(args);
    }

}
