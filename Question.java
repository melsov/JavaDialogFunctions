/**
 * Created by student on 6/2/16.
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.Optional;

import static jdk.nashorn.internal.objects.Global.println;


public class Questioner extends Application {

    private final int FRAME_WIDTH = 450;

    public static void main(String[] args) {

        launch(args);
    }

    private void saveThis(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        Button btn2 = new Button();
        btn2.setText("Say 'Hi World'");
        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hi World!");
            }
        });

        ImageView iv = getImageView();

        StackPane root = new StackPane();
        Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);
        VBox box = new VBox();
        box.getChildren().add(iv);
        HBox buttonBox = new HBox();
        buttonBox.getChildren().add(btn);
        buttonBox.getChildren().add(btn2);
        box.getChildren().add(buttonBox);

        root.getChildren().add(box);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ImageView viewFor(String path, int width) {
        width = width < 1 ? 100 : width;
        // load the image
        Image image = new Image(path == null || path == "" ? "biddy.jpg" : path);
        // resizes the image to have width of 100 while preserving the ratio and using
        // higher quality filtering method; this ImageView is also cached to
        // improve performance
        ImageView iv2 = new ImageView();
        iv2.setImage(image);
        iv2.setFitWidth(width);
        iv2.setPreserveRatio(true);
        iv2.setSmooth(true);
        iv2.setCache(true);

        return  iv2;
    }


    public String getNonYesNoAnswer(String question) {
        TextInputDialog dialog = new TextInputDialog("walter");
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText(question);
        dialog.setContentText(" ");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            return  result.get();
        }
        return null;
    }

    public int getAnswer(String question, String imagePath, String... bText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Question");
        alert.setHeaderText(question);
        alert.setContentText("Choose your option.");
        alert.setGraphic(viewFor(imagePath, 200));

        String[] text = new String[] { "No" , "Yes", "Maybe" };
        for(int i = 0; i < text.length; ++i) {
            text[i] = i < bText.length ? bText[i] : text[i];
        }

        ButtonType buttonTypeOne = new ButtonType(text[0]);
        ButtonType buttonTypeTwo = new ButtonType(text[1]);
        ButtonType buttonTypeThree = new ButtonType(text[2]);

        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            return 0;
        } else if (result.get() == buttonTypeTwo) {
            return 1;
        } else if (result.get() == buttonTypeThree) {
            return 2;
        }
        return -1;
    }

    public boolean getResponse(String question) {
        return getResponse(question, null);
    }
    public boolean getResponse(String question, String imagePath) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Question");
        alert.setHeaderText(question);
        alert.setContentText("What is your response?");
        alert.setGraphic(viewFor(imagePath, 200));

        ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeTwo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            return true;
        }
        return false;
    }

    @Override
    public void start(Stage primaryStage) {
        getNonYesNoAnswer("Look, a Text Input DialogLook, a Text Input Dialog Look, a Text Input Dialog Look, a Text Input Dialog Look, a Text Input Dialog");
        int answer = getAnswer("Good?", "frisbee-fail.png", "yea" , "nay", "don't know");
        System.out.println(answer);
        boolean theyROK = getResponse("Are you good?");


    }

    private ImageView getImageView() {
        // load the image
        Image image = new Image("biddy.jpg");

        // simple displays ImageView the image as is
        ImageView iv1 = new ImageView();
        iv1.setImage(image);

        // resizes the image to have width of 100 while preserving the ratio and using
        // higher quality filtering method; this ImageView is also cached to
        // improve performance
        ImageView iv2 = new ImageView();
        iv2.setImage(image);
        iv2.setFitWidth(FRAME_WIDTH);
        iv2.setPreserveRatio(true);
        iv2.setSmooth(true);
        iv2.setCache(true);

        return iv2;
    }
}
