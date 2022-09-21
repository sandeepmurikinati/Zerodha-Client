package com.hem.zerodha;

import com.hem.zerodha.service.TradingPane;
import com.hem.zerodha.service.ZerodhaService;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import static com.hem.zerodha.service.ZerodhaService.login;


public class ZerodhaClient extends Application {

    static Stage stage;
    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        GridPane grid = getLoginScreen();

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Welcome");

        primaryStage.show();
    }

    private static GridPane getLoginScreen() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Token:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        btn.setOnAction(e -> {
            try {
                login(userTextField.getText());
                stage.getScene().setRoot(new TradingPane());
            }catch (Throwable ex) {
                System.out.println("Failed to Login");
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Login Failed: " + ex.getMessage());
            }

        });

        return grid;
    }

    public static void main(String[] args) {
        launch();
    }
}
