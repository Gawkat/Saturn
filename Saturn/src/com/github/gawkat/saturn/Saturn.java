package com.github.gawkat.saturn;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Saturn extends Application {

	private Stage primaryStage;
	private BorderPane startWindow;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Saturn");

		initStartWindow();
	}

	public void initStartWindow() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Saturn.class
					.getResource("view/StartWindow.fxml"));
			startWindow = (BorderPane) loader.load();

			Scene scene = new Scene(startWindow);
			primaryStage.setScene(scene);

			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
