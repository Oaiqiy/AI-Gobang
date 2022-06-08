package dev.oaiqiy.gui;

import dev.oaiqiy.gobang.Role;
import dev.oaiqiy.gobang.Score;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import lombok.Data;

@Data
public class MainDisplay extends Application {

    public static BoardComponent boardComponent;

    @Override
    public void start(Stage stage) throws Exception {
        boardComponent = new BoardComponent();

        Scene scene = new Scene(boardComponent, 800, 800);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getId());
        launch(args);

    }

}
