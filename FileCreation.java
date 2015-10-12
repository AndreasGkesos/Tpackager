import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FileCreation extends BorderPane {

	public FileCreation() {
		// create UI
		VBox creationInfo = new VBox(10);
		creationInfo.setAlignment(Pos.CENTER);
		creationInfo.setMaxWidth(Double.MAX_VALUE);
		creationInfo.setMaxHeight(Double.MAX_VALUE);
		
		Label label = new Label("File Created...");
		label.setPadding(new Insets(5, 5 , 0, 5));
		label.setFont(Font.font(java.awt.Font.SANS_SERIF, FontWeight.THIN, 20));
		
		creationInfo.getChildren().add(label);
		creationInfo.setStyle("-fx-background-color: #ffffff;");
		this.setCenter(creationInfo);
	}
}