package com.javaproject.jeuespace;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SpaceMainMenu extends FXGLMenu {

    private VBox menuBox;
    private VBox levelSelectionBox;

    public SpaceMainMenu() {
        super(MenuType.MAIN_MENU);

        Font titleFont = FXGL.getAssetLoader().loadFont("Jersey10-Regular.ttf").newFont(48);
        Font buttonFont = FXGL.getAssetLoader().loadFont("Jersey10-Regular.ttf").newFont(24);

        Text title = new Text("Jeu de l'espace");
        title.setFont(titleFont);
        title.setFill(Color.WHITE);

        Button btnPlay = new Button("Jouer");
        btnPlay.setFont(buttonFont);
        btnPlay.setOnAction(e -> showLevelSelection());

        Button btnCredits = new Button("Crédits");
        btnCredits.setFont(buttonFont);
        btnCredits.setOnAction(e -> FXGL.getDialogService().showMessageBox("Développé par Junie"));

        menuBox = new VBox(20, title, btnPlay, btnCredits);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setTranslateX(FXGL.getAppWidth() / 2.0 - 100);
        menuBox.setTranslateY(FXGL.getAppHeight() / 2.0 - 100);

        // Level Selection
        Text levelTitle = new Text("Sélection de Niveau");
        levelTitle.setFont(titleFont);
        levelTitle.setFill(Color.WHITE);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        Button btnLevel1 = new Button("Niveau 1");
        btnLevel1.setFont(buttonFont);
        btnLevel1.setOnAction(e -> fireNewGame());

        grid.add(btnLevel1, 0, 0);

        Button btnBack = new Button("Retour");
        btnBack.setFont(buttonFont);
        btnBack.setOnAction(e -> showMainMenu());

        levelSelectionBox = new VBox(20, levelTitle, grid, btnBack);
        levelSelectionBox.setAlignment(Pos.CENTER);
        levelSelectionBox.setTranslateX(FXGL.getAppWidth() / 2.0 - 150);
        levelSelectionBox.setTranslateY(FXGL.getAppHeight() / 2.0 - 100);
        levelSelectionBox.setVisible(false);

        getContentRoot().getChildren().addAll(menuBox, levelSelectionBox);
    }

    private void showLevelSelection() {
        menuBox.setVisible(false);
        levelSelectionBox.setVisible(true);
    }

    private void showMainMenu() {
        menuBox.setVisible(true);
        levelSelectionBox.setVisible(false);
    }
}
