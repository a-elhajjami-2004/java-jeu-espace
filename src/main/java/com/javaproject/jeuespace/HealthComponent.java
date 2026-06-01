package com.javaproject.jeuespace;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.dsl.FXGL;

public class HealthComponent extends Component {
    private int hp;
    private int maxHp;

    public HealthComponent(int hp) {
        this.hp = hp;
        this.maxHp = hp;
    }

    public int getHp() {
        return hp;
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            hp = 0;
            // Gérer la mort de l'entité
            entity.removeFromWorld();
        }
    }

    public boolean isDead() {
        return hp <= 0;
    }
}
