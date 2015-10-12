import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PackagerMainWindow extends Application {
	
	public static final int PRIMARYSTAGEWIDTH = 650;
	public static final int PRIMARYSTAGEHEIGHT = 400;
	
	private Path jarPath, destinationPath, logoPath;
	private String author, logoFileName;
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("T Packager...");
		
		BorderPane bp = new BorderPane();
		BaseBorderPane base = new BaseBorderPane();
		bp.setCenter(new WelcomeLayoutP());
		bp.setBottom(base);
		Scene mainScene = new Scene(bp, PRIMARYSTAGEWIDTH, PRIMARYSTAGEHEIGHT);
		
		base.hideBackButton();
		base.hideFinishButton();
		
		// Navigation with buttons
		base.getNextButton().setOnAction( e -> {
			if (bp.getCenter() instanceof WelcomeLayoutP) {
				bp.setCenter(new FileSelection());
				base.unHideBackButton();
				base.hideExitButton();
				base.hideFinishButton();
			}
			else if (bp.getCenter() instanceof FileSelection) {
				jarPath = ((FileSelection) bp.getCenter()).getJarPath();
				destinationPath = ((FileSelection) bp.getCenter()).getDestinationPath();
				logoPath = ((FileSelection) bp.getCenter()).getLogoPath();
				author = ((FileSelection) bp.getCenter()).getAuthorTextField().getText();
				if (jarPath.toString().equals("")) {
					new Alert(primaryStage, "You must choose a jar file").showAndWait();
					bp.setCenter(new FileSelection());
				}
				else if(destinationPath.toString().equals("")) {
					new Alert(primaryStage, "You must choose where you want the created file to be stored").showAndWait();
					bp.setCenter(new FileSelection());
				}
				else if(logoPath.toString().equals("") && ((FileSelection) bp.getCenter()).isSelected()) {
					new Alert(primaryStage, "You must choose a logo image").showAndWait();
					bp.setCenter(new FileSelection());
				}
				else if (!checkIfLogoImageTypeIsICO()) {
					new Alert(primaryStage, "Image type must be .ico").showAndWait();
					bp.setCenter(new FileSelection());
				}
				else if(author.equals("")) {
					new Alert(primaryStage, "You must provide the name of the author of the application").showAndWait();
					bp.setCenter(new FileSelection());
				}
				else {
					bp.setCenter(new FileCreation());
					base.hideNextButton();
					base.hideBackButton();
					base.hideExitButton();
					base.unHideFinishButton();
					
					try {
						this.createJar();
					} catch (IOException ex) {
					}
				}
			}
		});
		
		base.getExitButton().setOnAction( e -> {
			if (bp.getCenter() instanceof WelcomeLayoutP) {
				Platform.exit();
			}
		});
		
		base.getBackButton().setOnAction( e -> {
			if (bp.getCenter() instanceof FileSelection) {
				bp.setCenter(new WelcomeLayoutP());
				base.hideBackButton();
				base.hideFinishButton();
				base.unHideExitButton();
			}
		});
		
		base.getFinishButton().setOnAction(e -> {
			if (bp.getCenter() instanceof FileCreation) {
				Platform.exit(); // prepei na balw check gia to an exei teleiwsei swsta i diadikasia
			}
		});
		
		primaryStage.setScene(mainScene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	private void createJar() throws IOException {
		createTXTFile();
		createBatFile();
		Process p;
		try {
			p = Runtime.getRuntime().exec("cmd /c makeJar.bat");
			p.waitFor();
		} catch (Exception e) {
		}
		deleteCreatedFiles();
	}
	
	private String findJarFileName() {
		String s = null;
		StringTokenizer st = new StringTokenizer(jarPath.toString(), "\\");
		int count = st.countTokens();
		for (int i = 0; i < count; i++) {
			if (st.hasMoreTokens()) {
				s = st.nextToken();
			}
		}
		return s;
	}
	
	// know there is a bug here and you have to always choose a logo file. For now...
	private boolean checkIfLogoImageTypeIsICO() {
		String s = null;
		StringTokenizer st = new StringTokenizer(logoPath.toString(), "\\");
		int count = st.countTokens();
		for (int i = 0; i < count; i++) {
			if (st.hasMoreTokens()) {
				s = st.nextToken();
			}
		}
		logoFileName = s; // I am using this method to find logo file name, so i don't have to use the same technique again when I actually need it
		if (s.substring(s.lastIndexOf(".") + 1, s.lastIndexOf(".") + 4).toLowerCase().equals("ico")) { return true; }
		else { return false; }
	}
	
	private void createTXTFile() {
		File txt = new File(System.getProperty("user.dir") + "\\" + "data.txt");
	    BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(txt));
			writer.write(findJarFileName().substring(0, findJarFileName().length() - 4)); // To onoma tou jar xwris to .jar
			writer.write("*");
			writer.write(author);
			writer.write("*");
			
			if (!logoPath.equals("")) {
				writer.write(logoFileName);
				writer.write("*");
			}

			writer.write(jarPath.toString().replace(findJarFileName(), ""));
			writer.close();
		} catch (IOException e) {
		}
	}
	
	private void createBatFile() {
		File bat = new File(System.getProperty("user.dir") + "\\" + "makeJar.bat");
	    BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(bat));
			writer.write("jar -cvfm ");
			if (logoPath.equals(null)) {
				writer.write("\"" + destinationPath + "\\" + findJarFileName() + "\"" + " manifest.txt " + "\"" + jarPath.toString() + "\"" + " *.class" + " *.png *.ico" + " data.txt");
			}
			else {
				writer.write("\"" + destinationPath + "\\" + findJarFileName() + "\"" + " manifest.txt " + "\"" + jarPath.toString() + "\"" + " *.class" + " *.png *.ico" + " data.txt " + " \"" + logoPath.toString() + "\"");
			}
			writer.close();
		} catch (IOException e) {
		}
	}
	
	private void deleteCreatedFiles() {
		try {
			Files.deleteIfExists(Paths.get("makeJar.bat"));
			Files.deleteIfExists(Paths.get("data.txt"));
		} catch (IOException e) {
		}
	}
}