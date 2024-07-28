package co.edu.uptcSoft.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
    	//launch(args);
        Login login = new Login();
        login.createWindow();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Button bton1 = new Button("Si");
        Button bton2 = new Button("No");
        Button bton3 = new Button("Cancelar");

        HBox diseño = new HBox(20);
        diseño.setAlignment(Pos.CENTER);
        diseño.getChildren().addAll(bton1, bton2, bton3);

        StackPane base = new StackPane();
        base.getChildren().add(diseño);

        stage.setTitle("Hello Application");
        stage.setScene(new Scene(base, 300, 200, Color.AQUA));
        stage.setResizable(false);
        stage.show();
    }
}