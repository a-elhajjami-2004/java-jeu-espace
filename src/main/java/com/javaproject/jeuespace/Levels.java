package com.javaproject.jeuespace;

import static com.almasb.fxgl.dsl.FXGL.*;
import javafx.util.Duration;

public class Levels {
    
    public static void level1() {
        for (int i = 0; i < 5; i++) {
            spawn("fighter", i * 100 + 80, 50);
        }
    }

    public static void level2() {
        for (int i = 0; i < 5; i++) {
            final int index = i;
            runOnce(() -> {
                spawn("bomber", index * 100 + 100, -50);
            }, Duration.seconds(i * 2));
        }
    }

    public static void level3() {
        for (int i = 0; i < 5; i++) {
            spawn("fighter", Math.random() * (getAppWidth() - 60) + 30, Math.random() * 100 + 50);
        }
        for (int i = 0; i < 3; i++) {
            runOnce(() -> {
                spawn("bomber", Math.random() * (getAppWidth() - 60) + 30, -50);
            }, Duration.seconds(i * 2));
        }
    }

    public static void level4() {
        for (int i = 0; i < 6; i++) {
            spawn("fighter", Math.random() * (getAppWidth() - 60) + 30, Math.random() * 100 + 50);
        }
        for (int i = 0; i < 6; i++) {
            runOnce(() -> {
                spawn("bomber", Math.random() * (getAppWidth() - 60) + 30, -50);
            }, Duration.seconds(i * 2));
        }
    }
}
