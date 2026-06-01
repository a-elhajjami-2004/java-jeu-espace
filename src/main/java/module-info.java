open module com.javaproject.jeuespace {
	requires javafx.controls;
	requires javafx.fxml;

	requires com.almasb.fxgl.all;

//	opens com.javaproject.jeuespace to javafx.fxml, com.almasb.fxgl.all;
//	opens assets.textures to com.almasb.fxgl.all;
//	opens assets.ui to com.almasb.fxgl.all;
//	opens assets.sounds to com.almasb.fxgl.all;
	exports com.javaproject.jeuespace;
}