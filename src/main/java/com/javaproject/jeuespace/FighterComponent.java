package com.javaproject.jeuespace;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.LocalTimer;
import javafx.util.Duration;

public class FighterComponent extends Component {
    private double speed = 150;
    private LocalTimer shootTimer = FXGL.newLocalTimer();
    private Duration nextShootDelay = Duration.seconds(1 + Math.random() * 2);

    @Override
    public void onUpdate(double tpf) {
        entity.translateX(-speed * tpf);

        if (entity.getRightX() < 0) {
            entity.setX(FXGL.getAppWidth());
        }

        if (shootTimer.elapsed(nextShootDelay)) {
            shoot();
            shootTimer.capture();
            nextShootDelay = Duration.seconds(1 + Math.random() * 2);
        }
    }

    private void shoot() {
        FXGL.spawn("rocket", new SpawnData(entity.getX() + 20, entity.getY() + 30));
    }
}
