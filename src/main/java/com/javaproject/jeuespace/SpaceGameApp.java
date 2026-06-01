package com.javaproject.jeuespace;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

public class SpaceGameApp extends GameApplication {

    private Entity player;
    private static int selectedLevel = 1;
    private int level;

    public static void setLevel(int level) {
        selectedLevel = level;
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(640);
        settings.setHeight(360);
        settings.setTitle("Jeu de l'espace");
        settings.setVersion("1.0");
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new SceneFactory() {
            @Override
            public FXGLMenu newMainMenu() {
                return new SpaceMainMenu();
            }
        });
    }

    @Override
    protected void initInput() {
        onKey(KeyCode.LEFT, "Move Left", () -> {
            if (player.getX() > 0) player.translateX(-5);
        });
        onKey(KeyCode.RIGHT, "Move Right", () -> {
            if (player.getRightX() < getAppWidth()) player.translateX(5);
        });
        onKey(KeyCode.UP, "Move Up", () -> {
            if (player.getY() > 0) player.translateY(-5);
        });
        onKey(KeyCode.DOWN, "Move Down", () -> {
            if (player.getBottomY() < getAppHeight()) player.translateY(5);
        });
        
        onKeyDown(KeyCode.SPACE, "Shoot", () -> {
            spawn("playerProjectile", player.getCenter().subtract(5, 20));
        });
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("score", 0);
        vars.put("hp", 8);
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new SpaceEntityFactory());
        this.level = selectedLevel;

        // Fond animé
        initBackground();
        
        player = spawn("player", getAppWidth() / 2.0 - 15, getAppHeight() - 50);
        
        // Spawn enemies based on level
        switch (level) {
            case 1: Levels.level1(); break;
            case 2: Levels.level2(); break;
            case 3: Levels.level3(); break;
            case 4: Levels.level4(); break;
            default: Levels.level1(); break;
        }
    }

    private void initBackground() {
        for (int i = 1; i <= 3; i++) {
            String name = String.format("background/layer0%d.png", i);
            AnimationChannel anim = new AnimationChannel(getAssetLoader().loadTexture(name).getImage(), 9, 640, 360, Duration.seconds(2), 0, 8);
            AnimatedTexture texture = new AnimatedTexture(anim);
            texture.loop();
            
            entityBuilder()
                    .view(texture)
                    .at(0, 0)
                    .zIndex(-10 + i)
                    .buildAndAttach();
        }
    }

    @Override
    protected void initPhysics() {
        onCollisionBegin(EntityType.PLAYER_PROJECTILE, EntityType.ENEMY, (proj, enemy) -> {
            spawn("explosion", enemy.getCenter().subtract(32, 32));
            proj.removeFromWorld();
            enemy.getComponent(HealthComponent.class).takeDamage(1);
            if (enemy.getComponent(HealthComponent.class).isDead()) {
                inc("score", 100);
            }
        });

        onCollisionBegin(EntityType.ENEMY_PROJECTILE, EntityType.PLAYER, (proj, player) -> {
            proj.removeFromWorld();
            player.getComponent(HealthComponent.class).takeDamage(1);
            inc("hp", -1);
            if (geti("hp") <= 0) {
                showMessage("Partie Perdue!", () -> getGameController().gotoMainMenu());
            }
        });
    }

    @Override
    protected void initUI() {
        Text scoreText = new Text();
        scoreText.setTranslateX(20);
        scoreText.setTranslateY(30);
        scoreText.setFill(Color.WHITE);
        scoreText.setFont(getAssetLoader().loadFont("Jersey10-Regular.ttf").newFont(24));
        scoreText.textProperty().bind(getip("score").asString("Score: %d"));
        addUINode(scoreText);

        ProgressBar hpBar = new ProgressBar();
        hpBar.setPrefWidth(200);
        hpBar.setTranslateX(20);
        hpBar.setTranslateY(40);
        hpBar.progressProperty().bind(getip("hp").divide(8.0));
        addUINode(hpBar);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
