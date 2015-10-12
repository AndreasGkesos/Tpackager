import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class WelcomeLayoutP extends BorderPane {

	public WelcomeLayoutP() {
		
		VBox imageBox = new VBox();
		Image image = new Image("Tlogo.png");
		ImageView imageView = new ImageView(image);
		imageBox.setAlignment(Pos.CENTER);
		imageBox.setMargin(imageView, new Insets(20));
		imageBox.setStyle("-fx-background-color: #ffffff;");

		Image infoImage = new Image("information-icon.png");
		ImageView infoImageView = new ImageView(infoImage);
		Label label = new Label();
		label.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		label.setGraphic(infoImageView);
		label.setTooltip(new Tooltip("This project is developed by Andreas Gkesos"));

		imageBox.getChildren().addAll(imageView, label);
		this.setLeft(imageBox);
		
		HBox labelBox = new HBox();
		Label welcomeLabel = new Label("Welcome to T Packager !!!");
		labelBox.setAlignment(Pos.CENTER);
		labelBox.setStyle("-fx-background-color: #ffffff;");
		welcomeLabel.setFont(Font.font(java.awt.Font.SANS_SERIF, FontWeight.THIN, 20));
	    final Reflection reflection = new Reflection();
	    reflection.setFraction(1.0);
	    welcomeLabel.setEffect(reflection);
		labelBox.getChildren().add(welcomeLabel);
		
		this.setCenter(labelBox);
	}
}