import gui.MainWindowLayout;

import java.awt.event.ActionEvent;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import simulation.Cycler;
import dataHandler.Properties;


public class Main extends Application implements EventHandler<javafx.event.ActionEvent> {
	static boolean cSettingsReady;
	static boolean eSettingsReady;
	static boolean pSettingsReady;
	public static void main(String[] args) {
		launch(args);
		//Cycler cycler = new Cycler(properties.getCycles());
		//cycler.cycle();

	}
	@Override
	public void start(Stage window) throws Exception{
		MainWindowLayout MWLayout = new MainWindowLayout();
		window.setMinHeight(200);
		window.setMinWidth(300);
		window.setTitle("Evo");
		BorderPane mainLayout = new BorderPane();
		mainLayout.setTop(MWLayout.getTopLayout());
		mainLayout.setCenter(MWLayout.getCenterLayout());
		Scene scene1 = new Scene(mainLayout);
		window.setScene(scene1);
		window.show();
		
	}
	@Override
	public void handle(javafx.event.ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
