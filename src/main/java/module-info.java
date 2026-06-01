module com.javaproject.jeuespace {
	requires javafx.controls;
	requires javafx.fxml;

	requires com.almasb.fxgl.all;

	opens com.javaproject.jeuespace to javafx.fxml;
	exports com.javaproject.jeuespace;
}