import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class FileSelection extends BorderPane {
	
	private FileChooser fileChooser;
	private DirectoryChooser directoryChooser;
	private Path jarPath, destinationPath, logoPath;
	private HBox logoSelectionBox;
	private TextField jarTextField, destinationTextField, authorTextField, logoTextField;
	private CheckBox checkBox;
	
	public FileSelection() {
		// Initialize 
		fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "\\" + "Desktop"));
		directoryChooser = new DirectoryChooser();
		directoryChooser.setInitialDirectory(new File(System.getProperty("user.home") + "\\" + "Desktop"));
		jarTextField = new TextField();
		jarPath = Paths.get("");
		destinationTextField = new TextField();
		destinationPath = Paths.get("");
		authorTextField = new TextField();
		logoTextField = new TextField();
		logoPath = Paths.get("");
		
		// Vertical Box
		VBox browseItems = new VBox(10);
		browseItems.setAlignment(Pos.CENTER);
		
		// First horizontal Box		
		HBox jarSelectionBox = new HBox(10);
		jarSelectionBox.setAlignment(Pos.BASELINE_LEFT);
		
		jarTextField.setMaxHeight(Double.MAX_VALUE);
		jarTextField.setPrefColumnCount(25);
		Button jarButton = new Button("Browse");
		jarButton.setMaxHeight(Double.MAX_VALUE);
		Image jarSelectionInfoImage = new Image("information-icon.png");
		ImageView jarSelectionInfoImageView = new ImageView(jarSelectionInfoImage);
		Label jarLabel = new Label();
		jarLabel.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		jarLabel.setGraphic(jarSelectionInfoImageView);
		jarLabel.setTooltip(new Tooltip("Choose the jar file of your application"));
		
		jarButton.setOnAction(e -> {
			jarPath = fileChooser.showOpenDialog(null).toPath();
			jarTextField.setText(jarPath.toString());			
		});
		
		jarSelectionBox.getChildren().addAll(jarTextField, jarButton, jarLabel);
		
		// Second horizontal Box
		HBox destinationBox = new HBox(10);
		destinationBox.setAlignment(Pos.BASELINE_LEFT);
		
		destinationTextField.setMaxHeight(Double.MAX_VALUE);
		destinationTextField.setPrefColumnCount(25);
		destinationTextField.setTooltip(new Tooltip("Choose the directory where you want the created jar to be stored"));
		Button destinationButton = new Button("Browse");
		destinationButton.setMaxHeight(Double.MAX_VALUE);
		
		destinationButton.setOnAction(e -> {
			destinationPath = directoryChooser.showDialog(null).toPath();
			destinationTextField.setText(destinationPath.toString());
		});
		
		destinationBox.getChildren().addAll(destinationTextField, destinationButton);
		
		// Third horizontal Box
		HBox authorBox = new HBox(10);
		authorBox.setAlignment(Pos.BASELINE_LEFT);
		
		authorTextField.setMaxHeight(Double.MAX_VALUE);
		authorTextField.setPrefColumnCount(25);
		authorTextField.setTooltip(new Tooltip("Name of developer/firm of the application"));
		
		authorBox.getChildren().add(authorTextField);
		
		// Fourth horizontal Box
		HBox optionalCheckBox = new HBox(10);
		optionalCheckBox.setAlignment(Pos.BASELINE_LEFT);
		
		checkBox = new CheckBox();
		checkBox.setSelected(true);
		Image checkBoxInfoImage = new Image("information-icon.png");
		ImageView checkBoxsInfoImageView = new ImageView(checkBoxInfoImage);
		Label checkBoxLabel = new Label();
		checkBoxLabel.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		checkBoxLabel.setGraphic(checkBoxsInfoImageView);
		checkBoxLabel.setTooltip(new Tooltip("Optional \nSelect checkbox if you want your own image for a logo \nImage type must be .ico"));
		
		checkBox.setOnAction( e -> {
			if (checkBox.isSelected()) {
				logoSelectionBox.setVisible(true);
			}
			else {
				logoSelectionBox.setVisible(false);
			}
		});
		
		optionalCheckBox.getChildren().addAll(checkBox, checkBoxLabel);
		
		// Fifth horizontal Box
		logoSelectionBox = new HBox(10);
		logoSelectionBox.setAlignment(Pos.BASELINE_LEFT);
		
		logoTextField.setMaxHeight(Double.MAX_VALUE);
		logoTextField.setPrefColumnCount(25);
		Button logoButton = new Button("Browse");
		logoButton.setMaxHeight(Double.MAX_VALUE);
		logoButton.setTooltip(new Tooltip("Image selection"));
		
		logoButton.setOnAction( e -> {
			logoPath = fileChooser.showOpenDialog(null).toPath();
			logoTextField.setText(logoPath.toString());
		});
		
		logoSelectionBox.getChildren().addAll(logoTextField, logoButton);
		
		// Put everything together
		browseItems.setStyle("-fx-background-color: #ffffff;");
		browseItems.setMargin(jarSelectionBox, new Insets(0, 0, 0, 130));
		browseItems.setMargin(destinationBox, new Insets(0, 0, 0, 130));
		browseItems.setMargin(authorBox, new Insets(0, 0, 0, 130));
		browseItems.setMargin(optionalCheckBox, new Insets(30, 0, 0, 130));
		browseItems.setMargin(logoSelectionBox, new Insets(0, 0, 0, 130));
		
		browseItems.getChildren().addAll(jarSelectionBox, destinationBox, authorBox, optionalCheckBox, logoSelectionBox);
		this.setCenter(browseItems);
	}

	public Path getJarPath() {
		return jarPath;
	}

	public Path getLogoPath() {
		return logoPath;
	}

	public TextField getAuthorTextField() {
		return authorTextField;
	}

	public Path getDestinationPath() {
		return destinationPath;
	}
	
	public boolean isSelected() {
		return checkBox.isSelected();
	}
}