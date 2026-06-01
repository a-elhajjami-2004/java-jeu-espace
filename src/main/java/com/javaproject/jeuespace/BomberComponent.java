package com.javaproject.jeuespace;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.LocalTimer;
import javafx.util.Duration;

public class BomberComponent extends Component {
    private double speed = 100;
    private LocalTimer shootTimer = FXGL.newLocalTimer();
    private boolean movingDown = true;

    @Override
    public void onUpdate(double tpf) {
        if (movingDown) {
            entity.translateY(speed * tpf);
            if (entity.getY() > 100) {
                movingDown = false;
            }
        } else {
            // Mouvement aléatoire horizontal ou stagnation
            entity.translateX((Math.random() - 0.5) * 200 * tpf);
            if (entity.getX() < 0) entity.setX(0);
            if (entity.getRightX() > FXGL.getAppWidth()) entity.setX(FXGL.getAppWidth() - entity.getWidth());
        }

        if (shootTimer.elapsed(Duration.seconds(3))) {
            shoot();
            shootTimer.capture();
        }
    }

    private void shoot() {
        FXGL.spawn("ray", new SpawnData(entity.getX() + 20, entity.getY() + 30));
    }
}
