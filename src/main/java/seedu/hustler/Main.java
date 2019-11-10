package seedu.hustler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Hustler using FXML.
 */
public class Main extends Application {

    private Hustler hustler = new Hustler();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setHustler(hustler);
            stage.show();
            stage.setOnCloseRequest(t -> Hustler.run("/bye"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
