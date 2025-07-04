package io.github.hra;

import java.lang.reflect.Method;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Kapka {
	private float speed;
	private float speedx;
	private Texture dropTexture;
	private Sprite sprite;

	public Kapka(float speed, Texture dropTexture, float worldWidth , float worldHeight) {
		super();
		this.speed = speed;
		this.dropTexture = dropTexture;
        sprite = new Sprite(dropTexture);
        sprite.setSize(1, 1);
        sprite.setX(MathUtils.random(0f, worldWidth - 1));
        sprite.setY(worldHeight);
        speedx = MathUtils.random(-0.5f, 0.5f);
        		//        sprite.setOrigin(50, 50);
	}

	public void draw(SpriteBatch batch) {
            sprite.draw(batch);
	}
	
	public void moveDown(float delta) {
		sprite.translate(speedx * delta, speed * delta);;
	}

	public float getY() {		
		return sprite.getY();
	}

	public float getX() {
		return sprite.getX();
	}

	public float getWidth() {
		return sprite.getWidth();
	}

	public float getHeight() {
		return sprite.getHeight();
	}
}
