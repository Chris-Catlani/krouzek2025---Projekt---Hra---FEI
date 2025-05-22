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
	}

	public void draw(SpriteBatch batch) {
            sprite.draw(batch);
	}
}
