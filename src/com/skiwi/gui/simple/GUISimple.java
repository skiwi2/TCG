
package com.skiwi.gui.simple;

import com.skiwi.gui.simple.console.ConsoleController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Frank van Heeswijk
 */
public class GUISimple extends Application {
    @Override
    public void start(final Stage primaryStage) throws IOException {
        primaryStage.setTitle("TCG Console");
        Parent root = FXMLLoader.load(ConsoleController.class.getResource("console.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
