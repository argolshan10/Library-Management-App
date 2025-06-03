package org.example;
import org.example.view.UserInterface ;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color ;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane ;
import javafx.scene.text.* ;
import javafx.scene.image.* ;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.w3c.dom.*;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        // Root layout
        VBox theRoot = new VBox(20); // spacing 20 between nodes
        theRoot.getStyleClass().add("theRoot");

        theRoot.setAlignment(Pos.CENTER);

        // Title Text
//        Text titleText = new Text("ALIREZA'S LIBRARY MANAGER");
//        titleText.setFont(Font.font("Arial", 30));
//        titleText.setFill(Color.WHITE);




        // Add everything to the layout
//        theRoot.getChildren().addAll(titleText);

        // Scene and Stage setup
        Scene scene = new Scene(theRoot, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("ALIREZA'S LIBRARY MANAGER");
        stage.setResizable(false);
        stage.setFullScreen(true);
        UserInterface my_obj = new UserInterface(theRoot) ;
        my_obj.displayMainMenu();
//        stage.setFullScreenExitHint("Press 1 to escape");
//        stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("1"));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/library.jfif")));

        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}