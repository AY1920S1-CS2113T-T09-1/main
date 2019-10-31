package seedu.hustler;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane{
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private TextArea console;
    @FXML
    private static PrintStream ps;
    @FXML
    private Button sendButton;
    @FXML
    private Button task;
    @FXML
    private Button taskCompletionMode;
    @FXML
    private Button achievement;
    @FXML
    private Button statistics;
    @FXML
    private Button avatar;
    @FXML
    private Button shop;
    @FXML
    private Button arena;
    @FXML
    private ImageView menu;
    @FXML
    private ImageView timer;
    @FXML
    private ImageView trophy;
    @FXML
    private ImageView spiderGraph;
    @FXML
    private ImageView profile;
    @FXML
    private ImageView trolley;
    @FXML
    private ImageView swords;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private FlowPane welcomeScreen;
    @FXML
    private FlowPane flowPane = new FlowPane();
    @FXML
    private ScrollPane scrollPANEE;
    @FXML
    private FlowPane heading;

    private Hustler hustler;

    /**
     * Initializes essential components to run Hustler.
     * @throws IOException if text area could not be found.
     */
    @FXML
    public void initialize() throws IOException {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        ps = new PrintStream(new Console(console));
        console.setEditable(false);
        onPrinting();
        Hustler.initialize();

        StackPane stackPane = new StackPane();
        Rectangle whiteSpace = new Rectangle();
        welcomeScreen.setStyle("-fx-background-color:#2dcb70");
        whiteSpace.setOpacity(0.0);
        whiteSpace.setHeight(50);
        whiteSpace.widthProperty().bind(flowPane.widthProperty());

        Text text = new Text("Hi, I am Hustler!\n What can I do for you?");
        text.setFont(Font.font("Gill Sans", 30));
        text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.CENTER);

        stackPane.setMargin(text,new Insets(0,50,0,50));
        stackPane.setAlignment(text,Pos.CENTER);
        stackPane.getChildren().addAll(text);
        stackPane.prefWidthProperty().bind(welcomeScreen.widthProperty());

        welcomeScreen.getChildren().addAll(whiteSpace,stackPane);
    }

    public void setHustler(Hustler h) {
        hustler = h;
    }

    public static void onPrinting() {
        System.setOut(ps);
        System.setErr(ps);
    }

    public static void offPrinting() {
        System.setOut(dummyStream);
        System.setErr(dummyStream);
    }

    /**
     * Changes printing stream to text area in GUI.
     */
    public class Console extends OutputStream {
        private TextArea console;

        public Console(TextArea console) {
            this.console = console;
        }

        public void appendText(String valueOf) {
            Platform.runLater(() -> console.appendText(valueOf));
        }

        public void write(int b) {
            appendText(String.valueOf((char)b));
        }
    }

    /**
     * Creates a dummy stream which does not print anything.
     */
    public static PrintStream dummyStream = new PrintStream(new OutputStream() {
        public void write(int b) {
        }
    });

    /**
     * Handles operations after each user's input.
     */
    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        if (!input.isBlank()) {
            removeWelcome();
            sendClicked();
            flowPane.setStyle("-fx-background-color:#ffffff");
            console.clear();
            if (input.equals("/achievements")) {
                achievementAction();
            } else if (input.equals("/avatar")) {
                avatarAction();
            } else if (input.equals("/shop")) {
                shopAction();
            } else {
                taskAction();
                Hustler.run(input);
            }
            scrollPANEE.setContent(console);
            userInput.clear();
            sendReleased();
        }
    }

    @FXML
    public void sendClicked() {
        ColorAdjust color = new ColorAdjust();
        color.setBrightness(-0.16);
        sendButton.setEffect(color);
    }

    @FXML
    public void sendReleased() {
        ColorAdjust color = new ColorAdjust();
        color.setBrightness(0);
        sendButton.setEffect(color);
    }

    @FXML
    public void removeWelcome() {
        rootPane.getChildren().remove(welcomeScreen);
    }

    @FXML
    public void taskAction() {
        heading.getChildren().clear();

        Text taskTitle = new Text("TASKS");
        taskTitle.setFont(Font.font("Gill Sans", 15));
        taskTitle.setFill(Color.GRAY);

        StackPane titlePane = new StackPane();
        titlePane.setAlignment(taskTitle, Pos.CENTER);
        titlePane.prefHeightProperty().bind(heading.prefHeightProperty());
        titlePane.prefWidthProperty().bind(heading.widthProperty());
        titlePane.getChildren().addAll(taskTitle);

        heading.getChildren().addAll(titlePane);

        ColorAdjust color = new ColorAdjust();
        color.setContrast(0.35);
        color.setHue(-0.21);
        color.setBrightness(0.09);
        color.setSaturation(1.0);
        menu.setEffect(color);
        timer.setEffect(null);
        trophy.setEffect(null);
        spiderGraph.setEffect(null);
        profile.setEffect(null);
        trolley.setEffect(null);
        swords.setEffect(null);

        task.setStyle("-fx-background-color:#243342");
        taskCompletionMode.setStyle("-fx-background-color:#34495E");
        achievement.setStyle("-fx-background-color:#34495E");
        statistics.setStyle("-fx-background-color:#34495E");
        avatar.setStyle("-fx-background-color:#34495E");
        shop.setStyle("-fx-background-color:#34495E");
        arena.setStyle("-fx-background-color:#34495E");
        //settings.setStyle("-fx-background-color:#34495E");

        task.textFillProperty().setValue(Paint.valueOf("#ffffff"));
        taskCompletionMode.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        achievement.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        statistics.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        avatar.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        shop.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        arena.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        // settings.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
    }

    @FXML
    public void taskCompletionModeAction() {
        heading.getChildren().clear();

        Text taskCompletionTitle = new Text("TASK COMPLETION MODE");
        taskCompletionTitle.setFont(Font.font("Gill Sans", 15));
        taskCompletionTitle.setFill(Color.GRAY);

        StackPane titlePane = new StackPane();
        titlePane.setAlignment(taskCompletionTitle, Pos.CENTER);
        titlePane.prefHeightProperty().bind(heading.prefHeightProperty());
        titlePane.prefWidthProperty().bind(heading.widthProperty());
        titlePane.getChildren().addAll(taskCompletionTitle);

        heading.getChildren().addAll(titlePane);

        flowPane.setStyle("-fx-background-color:#ffffff");
        flowPane.getChildren().clear();

        ColorAdjust color = new ColorAdjust();
        color.setContrast(0.35);
        color.setHue(-0.21);
        color.setBrightness(0.09);
        color.setSaturation(1.0);

        menu.setEffect(null);
        timer.setEffect(color);
        trophy.setEffect(null);
        spiderGraph.setEffect(null);
        profile.setEffect(null);
        trolley.setEffect(null);
        swords.setEffect(null);

        task.setStyle("-fx-background-color:#34495E");
        taskCompletionMode.setStyle("-fx-background-color:#243342");
        achievement.setStyle("-fx-background-color:#34495E");
        statistics.setStyle("-fx-background-color:#34495E");
        avatar.setStyle("-fx-background-color:#34495E");
        shop.setStyle("-fx-background-color:#34495E");
        arena.setStyle("-fx-background-color:#34495E");
        //settings.setStyle("-fx-background-color:#34495E");

        task.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        taskCompletionMode.textFillProperty().setValue(Paint.valueOf("#ffffff"));
        achievement.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        statistics.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        avatar.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        shop.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        arena.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        //settings.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
    }

    @FXML
    public void achievementAction() {
        heading.getChildren().clear();

        Text achievementTitle = new Text("ACHIEVEMENTS");
        achievementTitle.setFont(Font.font("Gill Sans", 15));
        achievementTitle.setFill(Color.GRAY);

        StackPane titlePane = new StackPane();
        titlePane.setAlignment(achievementTitle, Pos.CENTER);
        titlePane.prefHeightProperty().bind(heading.prefHeightProperty());
        titlePane.prefWidthProperty().bind(heading.widthProperty());
        titlePane.getChildren().addAll(achievementTitle);

        heading.getChildren().addAll(titlePane);

        ColorAdjust color = new ColorAdjust();
        color.setContrast(0.35);
        color.setHue(-0.21);
        color.setBrightness(0.09);
        color.setSaturation(1.0);

        menu.setEffect(null);
        timer.setEffect(null);
        trophy.setEffect(color);
        spiderGraph.setEffect(null);
        profile.setEffect(null);
        trolley.setEffect(null);
        swords.setEffect(null);

        task.setStyle("-fx-background-color:#34495E");
        taskCompletionMode.setStyle("-fx-background-color:#34495E");
        achievement.setStyle("-fx-background-color:#243342");
        statistics.setStyle("-fx-background-color:#34495E");
        avatar.setStyle("-fx-background-color:#34495E");
        shop.setStyle("-fx-background-color:#34495E");
        arena.setStyle("-fx-background-color:#34495E");
        //settings.setStyle("-fx-background-color:#34495E");

        task.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        taskCompletionMode.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        achievement.textFillProperty().setValue(Paint.valueOf("#ffffff"));
        statistics.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        avatar.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        shop.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        arena.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        //settings.textFillProperty().setValue(Paint.valueOf("#95a5a6"));

        Hustler.run("/achievements");
    }

    @FXML
    public void statisticsAction() {
        heading.getChildren().clear();

        Text statisticsTitle = new Text("STATISTICS");
        statisticsTitle.setFont(Font.font("Gill Sans", 15));
        statisticsTitle.setFill(Color.GRAY);

        StackPane titlePane = new StackPane();
        titlePane.setAlignment(statisticsTitle, Pos.CENTER);
        titlePane.prefHeightProperty().bind(heading.prefHeightProperty());
        titlePane.prefWidthProperty().bind(heading.widthProperty());
        titlePane.getChildren().addAll(statisticsTitle);

        heading.getChildren().addAll(titlePane);

        flowPane.setStyle("-fx-background-color:#ffffff");
        flowPane.getChildren().clear();

        ColorAdjust color = new ColorAdjust();
        color.setContrast(0.35);
        color.setHue(-0.21);
        color.setBrightness(0.09);
        color.setSaturation(1.0);

        menu.setEffect(null);
        timer.setEffect(null);
        trophy.setEffect(null);
        spiderGraph.setEffect(color);
        profile.setEffect(null);
        trolley.setEffect(null);
        swords.setEffect(null);

        task.setStyle("-fx-background-color:#34495E");
        taskCompletionMode.setStyle("-fx-background-color:#34495E");
        achievement.setStyle("-fx-background-color:#34495E");
        this.statistics.setStyle("-fx-background-color:#243342");
        avatar.setStyle("-fx-background-color:#34495E");
        shop.setStyle("-fx-background-color:#34495E");
        arena.setStyle("-fx-background-color:#34495E");
        //settings.setStyle("-fx-background-color:#34495E");

        task.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        taskCompletionMode.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        achievement.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        this.statistics.textFillProperty().setValue(Paint.valueOf("#ffffff"));
        avatar.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        shop.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        arena.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        //settings.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
    }

    @FXML
    public void avatarAction() {
        heading.getChildren().clear();

        Text avatarTitle = new Text("AVATAR");
        avatarTitle.setFont(Font.font("Gill Sans", 15));
        avatarTitle.setFill(Color.GRAY);

        StackPane titlePane = new StackPane();
        titlePane.setAlignment(avatarTitle, Pos.CENTER);
        titlePane.prefHeightProperty().bind(heading.prefHeightProperty());
        titlePane.prefWidthProperty().bind(heading.widthProperty());
        titlePane.getChildren().addAll(avatarTitle);

        heading.getChildren().addAll(titlePane);

        flowPane.setStyle("-fx-background-color:#ffffff");
        flowPane.getChildren().clear();

        ColorAdjust color = new ColorAdjust();
        color.setContrast(0.35);
        color.setHue(-0.21);
        color.setBrightness(0.09);
        color.setSaturation(1.0);

        menu.setEffect(null);
        timer.setEffect(null);
        trophy.setEffect(null);
        spiderGraph.setEffect(null);
        profile.setEffect(color);
        trolley.setEffect(null);
        swords.setEffect(null);

        task.setStyle("-fx-background-color:#34495E");
        taskCompletionMode.setStyle("-fx-background-color:#34495E");
        achievement.setStyle("-fx-background-color:#34495E");
        statistics.setStyle("-fx-background-color:#34495E");
        avatar.setStyle("-fx-background-color:#243342");
        shop.setStyle("-fx-background-color:#34495E");
        arena.setStyle("-fx-background-color:#34495E");
        //settings.setStyle("-fx-background-color:#34495E");

        task.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        taskCompletionMode.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        achievement.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        statistics.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        avatar.textFillProperty().setValue(Paint.valueOf("#ffffff"));
        shop.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        arena.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        //settings.textFillProperty().setValue(Paint.valueOf("#95a5a6"));

        console.clear();
        Hustler.run("/avatar");
    }

    @FXML
    public void shopAction() {
        heading.getChildren().clear();

        Text title1 = new Text("SHOP");
        title1.setFont(Font.font("Gill Sans", 15));
        title1.setFill(Color.GRAY);

        StackPane titlePane = new StackPane();
        //titlePane.setMargin(title1, new Insets(0,0,0,50));
        titlePane.setAlignment(title1, Pos.CENTER);
        titlePane.prefHeightProperty().bind(heading.prefHeightProperty());
        titlePane.prefWidthProperty().bind(heading.widthProperty());
        titlePane.getChildren().addAll(title1);

        heading.getChildren().addAll(titlePane);

        flowPane.setStyle("-fx-background-color:#ffffff");
        flowPane.getChildren().clear();

        ColorAdjust color = new ColorAdjust();
        color.setContrast(0.35);
        color.setHue(-0.21);
        color.setBrightness(0.09);
        color.setSaturation(1.0);

        menu.setEffect(null);
        timer.setEffect(null);
        trophy.setEffect(null);
        spiderGraph.setEffect(null);
        profile.setEffect(null);
        trolley.setEffect(color);
        swords.setEffect(null);

        task.setStyle("-fx-background-color:#34495E");
        taskCompletionMode.setStyle("-fx-background-color:#34495E");
        achievement.setStyle("-fx-background-color:#34495E");
        statistics.setStyle("-fx-background-color:#34495E");
        avatar.setStyle("-fx-background-color:#34495E");
        shop.setStyle("-fx-background-color:#243342");
        arena.setStyle("-fx-background-color:#34495E");
        //settings.setStyle("-fx-background-color:#34495E");

        task.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        taskCompletionMode.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        achievement.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        statistics.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        avatar.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        shop.textFillProperty().setValue(Paint.valueOf("#ffffff"));
        arena.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        //settings.textFillProperty().setValue(Paint.valueOf("#95a5a6"));

        Hustler.run("/shop");
    }

    @FXML
    public void arenaAction() {
        heading.getChildren().clear();

        Text arenaAction = new Text("ARENA");
        arenaAction.setFont(Font.font("Gill Sans", 15));
        arenaAction.setFill(Color.GRAY);

        StackPane titlePane = new StackPane();
        //titlePane.setMargin(title1, new Insets(0,0,0,50));
        titlePane.setAlignment(arenaAction, Pos.CENTER);
        titlePane.prefHeightProperty().bind(heading.prefHeightProperty());
        titlePane.prefWidthProperty().bind(heading.widthProperty());
        titlePane.getChildren().addAll(arenaAction);

        heading.getChildren().addAll(titlePane);

        flowPane.setStyle("-fx-background-color:#ffffff");
        flowPane.getChildren().clear();

        ColorAdjust color = new ColorAdjust();
        color.setContrast(0.35);
        color.setHue(-0.21);
        color.setBrightness(0.09);
        color.setSaturation(1.0);

        menu.setEffect(null);
        timer.setEffect(null);
        trophy.setEffect(null);
        spiderGraph.setEffect(null);
        profile.setEffect(null);
        trolley.setEffect(null);
        swords.setEffect(color);

        task.setStyle("-fx-background-color:#34495E");
        taskCompletionMode.setStyle("-fx-background-color:#34495E");
        achievement.setStyle("-fx-background-color:#34495E");
        statistics.setStyle("-fx-background-color:#34495E");
        avatar.setStyle("-fx-background-color:#34495E");
        shop.setStyle("-fx-background-color:#34495E");
        arena.setStyle("-fx-background-color:#243342");
        //settings.setStyle("-fx-background-color:#34495E");

        achievement.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        task.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        taskCompletionMode.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        statistics.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        avatar.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        shop.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
        arena.textFillProperty().setValue(Paint.valueOf("#ffffff"));
        //settings.textFillProperty().setValue(Paint.valueOf("#95a5a6"));
    }
}
