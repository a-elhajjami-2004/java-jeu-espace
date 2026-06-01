package com.javaproject.jeuespace;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.Texture;
import javafx.scene.image.Image;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class SpaceEntityFactory implements EntityFactory {

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        AnimatedTexture engineEffect = createEngineEffect("playerShip/engineEffects/base_powering.png", 4, 48);
        
        return entityBuilder(data)
                .type(EntityType.PLAYER)
                .view(engineEffect)
                .viewWithBBox("playerShip/bases/fullHealth.png")
                .with(new CollidableComponent(true))
                .with(new HealthComponent(8))
                .build();
    }

    private AnimatedTexture createEngineEffect(String textureName, int frames, int frameSize) {
        AnimationChannel anim = new AnimationChannel(getAssetLoader().loadTexture(textureName).getImage(), frames, frameSize, frameSize, Duration.seconds(0.5), 0, frames - 1);
        AnimatedTexture texture = new AnimatedTexture(anim);
        texture.loop();
        return texture;
    }

    @Spawns("fighter")
    public Entity newFighter(SpawnData data) {
        AnimatedTexture engine = createEngineEffect("enemyShip/engineEffects/fighter.png", 8, 64);
        
        Entity entity = entityBuilder(data)
                .type(EntityType.ENEMY)
                .view(engine)
                .viewWithBBox("enemyShip/bases/fighter.png")
                .with(new CollidableComponent(true))
                .with(new HealthComponent(4))
                .with(new FighterComponent())
                .build();
        
        entity.setRotation(180);
        return entity;
    }

    @Spawns("bomber")
    public Entity newBomber(SpawnData data) {
        AnimatedTexture engine = createEngineEffect("enemyShip/engineEffects/bomber.png", 8, 64);

        Entity entity = entityBuilder(data)
                .type(EntityType.ENEMY)
                .view(engine)
                .viewWithBBox("enemyShip/bases/bomber.png")
                .with(new CollidableComponent(true))
                .with(new HealthComponent(5))
                .with(new BomberComponent())
                .build();
        
        entity.setRotation(180);
        return entity;
    }

    private AnimatedTexture createProjectile(String textureName, int frames, int frameWidth, int frameHeight) {
        Image projectileSpritesheet = getAssetLoader().loadTexture(textureName).getImage();
        AnimationChannel projectileAnim = new AnimationChannel(projectileSpritesheet, frames, frameWidth, frameHeight, Duration.seconds(0.25), 0, frames - 1);
        AnimatedTexture projectileTexture = new AnimatedTexture(projectileAnim);
        projectileTexture.loop();
        return projectileTexture;
    }

    @Spawns("playerProjectile")
    public Entity newPlayerProjectile(SpawnData data) {
        AnimatedTexture projectileTexture = createProjectile("playerShip/projectiles/autoCannonBullet.png", 4, 32, 32);

        Entity projectileEntity = entityBuilder(data)
                           .type(EntityType.PLAYER_PROJECTILE)
                .view(projectileTexture.loop())
                           .with(new CollidableComponent(true))
                           .with(new ProjectileComponent(400, true))
                           .build();

//        projectileEntity.getViewComponent().addChild(projectileTexture);

        return projectileEntity;
    }

    @Spawns("rocket")
    public Entity newRocket(SpawnData data) {
        AnimatedTexture rocketTexture = createProjectile("enemyShip/projectiles/rocket.png", 4, 8, 16);

        Entity entity = entityBuilder(data)
                .type(EntityType.ENEMY_PROJECTILE)
//                .viewWithBBox("enemyShip/projectiles/Nairan - Rocket.png")
                .with(new CollidableComponent(true))
                .with(new ProjectileComponent(300, false))
                .build();

        entity.getViewComponent().addChild(rocketTexture);
        entity.setRotation(180);
        return entity;
    }

    @Spawns("ray")
    public Entity newRay(SpawnData data) {
        AnimatedTexture rayTexture = createProjectile("enemyShip/projectiles/ray.png", 4, 18, 38);

        Entity entity = entityBuilder(data)
                .type(EntityType.ENEMY_PROJECTILE)
//                .viewWithBBox("enemyShip/projectiles/Nairan - Ray.png")
                .with(new CollidableComponent(true))
                .with(new ProjectileComponent(500, false))
                .build();

        entity.getViewComponent().addChild(rayTexture);
        entity.setRotation(180);
        return entity;
    }

    @Spawns("explosion")
    public Entity newExplosion(SpawnData data) {
        AnimationChannel anim = new AnimationChannel(getAssetLoader().loadTexture("enemyShip/destruction/fighter.png").getImage(), 18, 64, 64, Duration.seconds(0.5), 0, 17);
        AnimatedTexture texture = new AnimatedTexture(anim);
        
        return entityBuilder(data)
                .view(texture.play())
                .with(new com.almasb.fxgl.dsl.components.ExpireCleanComponent(Duration.seconds(0.5)))
                .build();
    }
}
