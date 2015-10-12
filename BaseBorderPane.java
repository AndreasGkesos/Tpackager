import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class BaseBorderPane extends HBox {
	
	private Button backButton, nextButton, exitButton, finishButton;
	
	public BaseBorderPane() {
		
		this.setSpacing(10);
		this.setAlignment(Pos.CENTER);
		backButton = new Button("Back");
		nextButton = new Button("Next");
		exitButton = new Button("Exit");
		finishButton = new Button("Finish");
		
		this.setStyle("-fx-background-color: #ffffff;");
		this.getChildren().addAll(backButton, nextButton, exitButton, finishButton);
	}

	public void hideBackButton() {
		backButton.setVisible(false);
	}
	
	public void unHideBackButton() {
		backButton.setVisible(true);
	}
	
	public void hideExitButton() {
		exitButton.setVisible(false);
	}
	
	public void unHideExitButton() {
		exitButton.setVisible(true);
	}
	
	public void hideFinishButton() {
		finishButton.setVisible(false);
	}
	
	public void unHideFinishButton() {
		finishButton.setVisible(true);
	}
	
	public void hideNextButton() {
		nextButton.setVisible(false);
	}

	public Button getBackButton() {
		return backButton;
	}

	public void setBackButton(Button backButton) {
		this.backButton = backButton;
	}

	public Button getNextButton() {
		return nextButton;
	}

	public void setNextButton(Button nextButton) {
		this.nextButton = nextButton;
	}

	public Button getExitButton() {
		return exitButton;
	}

	public void setExitButton(Button exitButton) {
		this.exitButton = exitButton;
	}
	
	public Button getFinishButton() {
		return finishButton;
	}

	public void setFinishButton(Button finishButton) {
		this.finishButton = finishButton;
	}
}