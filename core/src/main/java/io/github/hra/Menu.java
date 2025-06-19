package io.github.hra;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Menu implements Screen {
	private static final Color BLACK = null;
	private Main main;
	private SpriteBatch batch;
	private Texture backgroundTexture;
	private BitmapFont font;

	public Menu(Main main) {
		super();
		this.main = main;
		font = new BitmapFont();
		batch = new SpriteBatch();
	}

	@Override
	public void show() {
		backgroundTexture = new Texture("background.png");

	}

	@Override
	public void render(float delta) {
		float worldWidth = 640;
		float worldHeight = 480;
		batch.begin();
		batch.draw(backgroundTexture, 0, 0, worldWidth, worldHeight);
		font.draw(batch, "Welcome to Drop!!! ", 40, 450.5f);
		font.draw(batch, "Tap anywhere to begin!", 40, 50);
		if (Gdx.input.isButtonPressed(0)) {
			main.setScreen(new Hra());
		}
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}

