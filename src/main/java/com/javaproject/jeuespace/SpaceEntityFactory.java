package com.javaproject.jeuespace;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class SpaceEntityFactory implements EntityFactory {

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        AnimatedTexture engine = createEngine("playerShip/engines/Main Ship - Engines - Base Engine - Spritesheet.png", 4, 2);
        
        Entity entity = entityBuilder(data)
                .type(EntityType.PLAYER)
                .viewWithBBox("playerShip/bases/fullHealth.png")
                .with(new CollidableComponent(true))
                .with(new HealthComponent(8))
                .build();
        
        entity.getViewComponent().addChild(engine);
        engine.setTranslateY(30); // Positionner le moteur sous le vaisseau
        
        return entity;
    }

    private AnimatedTexture createEngine(String name, int cols, int rows) {
        AnimationChannel anim = new AnimationChannel(getAssetLoader().loadTexture(name).getImage(), cols, 48, 48, Duration.seconds(0.5), 0, cols * rows - 1);
        AnimatedTexture texture = new AnimatedTexture(anim);
        texture.loop();
        return texture;
    }

    @Spawns("fighter")
    public Entity newFighter(SpawnData data) {
        AnimatedTexture engine = createEngine("enemyShip/engines/Nairan - Fighter - Engine.png", 8, 1);
        
        Entity entity = entityBuilder(data)
                .type(EntityType.ENEMY)
                .viewWithBBox("enemyShip/bases/Nairan - Fighter - Base.png")
                .with(new CollidableComponent(true))
                .with(new HealthComponent(4))
                .with(new FighterComponent())
                .build();
        
        entity.getViewComponent().addChild(engine);
        engine.setTranslateY(-20); // Moteur au-dessus car tourné
        entity.setRotation(180);
        return entity;
    }

    @Spawns("bomber")
    public Entity newBomber(SpawnData data) {
        AnimatedTexture engine = createEngine("enemyShip/engines/Nairan - Bomber - Engine.png", 8, 1);

        Entity entity = entityBuilder(data)
                .type(EntityType.ENEMY)
                .viewWithBBox("enemyShip/bases/Nairan - Bomber - Base.png")
                .with(new CollidableComponent(true))
                .with(new HealthComponent(5))
                .with(new BomberComponent())
                .build();
        
        entity.getViewComponent().addChild(engine);
        engine.setTranslateY(-20);
        entity.setRotation(180);
        return entity;
    }

    @Spawns("playerProjectile")
    public Entity newPlayerProjectile(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.PLAYER_PROJECTILE)
                .viewWithBBox("playerShip/projectiles/Main ship weapon - Projectile - Auto cannon bullet.png")
                .with(new CollidableComponent(true))
                .with(new ProjectileComponent(400, true))
                .build();
    }

    @Spawns("rocket")
    public Entity newRocket(SpawnData data) {
        Entity entity = entityBuilder(data)
                .type(EntityType.ENEMY_PROJECTILE)
                .viewWithBBox("enemyShip/projectiles/Nairan - Rocket.png")
                .with(new CollidableComponent(true))
                .with(new ProjectileComponent(300, false))
                .build();
        entity.setRotation(180);
        return entity;
    }

    @Spawns("ray")
    public Entity newRay(SpawnData data) {
        Entity entity = entityBuilder(data)
                .type(EntityType.ENEMY_PROJECTILE)
                .viewWithBBox("enemyShip/projectiles/Nairan - Ray.png")
                .with(new CollidableComponent(true))
                .with(new ProjectileComponent(500, false))
                .build();
        entity.setRotation(180);
        return entity;
    }

    @Spawns("explosion")
    public Entity newExplosion(SpawnData data) {
        AnimationChannel anim = new AnimationChannel(getAssetLoader().loadTexture("enemyShip/destruction/Nairan - Fighter -  Destruction.png").getImage(), 18, 64, 64, Duration.seconds(0.5), 0, 17);
        AnimatedTexture texture = new AnimatedTexture(anim);
        
        return entityBuilder(data)
                .view(texture.play())
                .with(new com.almasb.fxgl.dsl.components.ExpireCleanComponent(Duration.seconds(0.5)))
                .build();
    }
}
