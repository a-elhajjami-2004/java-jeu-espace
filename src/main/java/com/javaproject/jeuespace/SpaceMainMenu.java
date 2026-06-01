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
        btnCredits.setOnAction(e ->
                FXGL.getDialogService().showMessageBox("Développé par Adam, Djelika, Hajar, Abdelilah")
        );

        Button btnExit = new Button("Quitter");
        btnExit.setFont(buttonFont);
        btnExit.setOnAction(e -> fireExit());

        menuBox = new VBox(20, title, btnPlay, btnCredits, btnExit);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setTranslateX(FXGL.getAppWidth() / 2.0 - 100);
        menuBox.setTranslateY(FXGL.getAppHeight() / 2.0 - 100);

        Text levelTitle = new Text("Sélection de Niveau");
        levelTitle.setFont(titleFont);
        levelTitle.setFill(Color.WHITE);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        Button btnLevel1 = new Button("Niveau 1");
        btnLevel1.setFont(buttonFont);
        btnLevel1.setOnAction(e -> {
            FXGL.set("selectedLevel", 1);
            fireNewGame();
        });

        Button btnLevel2 = new Button("Niveau 2");
        btnLevel2.setFont(buttonFont);
        btnLevel2.setOnAction(e -> {
            FXGL.set("selectedLevel", 2);
            fireNewGame();
        });

        Button btnLevel3 = new Button("Niveau 3");
        btnLevel3.setFont(buttonFont);
        btnLevel3.setOnAction(e -> {
            FXGL.set("selectedLevel", 3);
            fireNewGame();
        });

        Button btnLevel4 = new Button("Niveau 4");
        btnLevel4.setFont(buttonFont);
        btnLevel4.setOnAction(e -> {
            FXGL.set("selectedLevel", 4);
            fireNewGame();
        });

        grid.add(btnLevel1, 0, 0);
        grid.add(btnLevel2, 1, 0);
        grid.add(btnLevel3, 0, 1);
        grid.add(btnLevel4, 1, 1);

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