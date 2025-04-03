package io.github.hra;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Menu implements Screen{

	private static final Color BLACK = null;
	Main main;
	SpriteBatch batch;
	BitmapFont font;
	
	public Menu(Main main) {
		super();
		this.main = main;
		font = new BitmapFont();
		batch = new SpriteBatch();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(Color.CORAL);
		batch.begin();
		font.draw(batch, "Welcome to Drop!!! ", 1, 450.5f);
		font.draw(batch, "Tap anywhere to begin!", 1, 50);
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
