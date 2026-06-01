package com.javaproject.jeuespace;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SpacePauseMenu extends FXGLMenu {

    public SpacePauseMenu() {
        super(MenuType.GAME_MENU);

        Button btnResume = new Button("Reprendre");
        btnResume.setOnAction(e -> fireResume());

        Button btnMainMenu = new Button("Menu principal");
        btnMainMenu.setOnAction(e -> fireExitToMainMenu());
        
       Button btnRestart = new Button("Recommencer");
       btnRestart.setOnAction(e -> fireNewGame());

        VBox pauseBox = new VBox(20, btnResume, btnMainMenu, btnRestart);
        pauseBox.setAlignment(Pos.CENTER);
        pauseBox.setTranslateX(300);
        pauseBox.setTranslateY(250);

        getContentRoot().getChildren().add(pauseBox);
    }
}
