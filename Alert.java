import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Alert extends Stage {

    public Alert(Stage stage, String message) {
        setResizable(false);
        initModality(Modality.APPLICATION_MODAL);
        initStyle(StageStyle.TRANSPARENT);
        
        Label label = new Label(message);
        label.setWrapText(true);
        label.setGraphicTextGap(20);
        label.setStyle("-fx-font: 15px Serif;");

        Button button = new Button("OK");
        button.setOnAction(e -> {
        	Alert.this.close();
        });

        BorderPane borderPane = new BorderPane();     
        borderPane.setMargin(label, new Insets(10));
        borderPane.setTop(label);

        HBox hbox = new HBox(20);
        hbox.setAlignment(Pos.CENTER);
        hbox.setMargin(button, new Insets(10));
        hbox.getChildren().add(button);
        hbox.setStyle("-fx-background-color: #cccccc;");
        hbox.setStyle("-fx-background-radius: 5;");
        hbox.setStyle("-fx-padding: 10;");
        hbox.setStyle("-fx-font: 15px Serif;");
        borderPane.setBottom(hbox);
        borderPane.setStyle("-fx-border-style: solid;");
        
        final Text text = new Text(message);
        text.snapshot(null, null);
        int width = (int) text.getLayoutBounds().getWidth() + 40;
        int height = 100;

        final Scene scene = new Scene(borderPane, width, height);
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);

        setX(stage.getX() + (stage.getWidth() / 2 - width / 2));
        setY(stage.getY() + (stage.getHeight() / 2 - height / 2));
    }
}