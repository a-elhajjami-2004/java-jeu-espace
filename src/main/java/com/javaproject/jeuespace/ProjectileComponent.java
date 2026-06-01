package com.javaproject.jeuespace;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;

public class ProjectileComponent extends Component {
    private double speed;
    private boolean up;

    public ProjectileComponent(double speed, boolean up) {
        this.speed = speed;
        this.up = up;
    }

    @Override
    public void onUpdate(double tpf) {
        if (up) {
            entity.translateY(-speed * tpf);
        } else {
            entity.translateY(speed * tpf);
        }

        if (entity.getY() < -100 || entity.getY() > FXGL.getAppHeight() + 100) {
            entity.removeFromWorld();
        }
    }
}
