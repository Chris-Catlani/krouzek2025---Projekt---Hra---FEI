package io.github.hra;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private int counter=0;
    private SpriteBatch spriteBatch;
    private FitViewport viewport;
    
    private Texture backgroundTexture;
    private Texture bucketTexture;
    private Texture dropTexture;
    private Sound dropSound;
    private Music music;
    private Sprite bucketSprite;
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        backgroundTexture = new Texture("background.png");
        bucketTexture = new Texture("bucket.png");
        dropTexture = new Texture("drop.png");
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.mp3"));
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(8, 5);
        bucketSprite = new Sprite(bucketTexture); // Initialize the sprite based on the texture
    }
    
    
    
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true); // true centers the camera
    }

    
    @Override
    public void render() {
    	 // organize code into three methods
        input();
        logic();
        draw();
    }

    
    
    private void draw() {
		// TODO Auto-generated method stub
    	ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        spriteBatch.draw(backgroundTexture, 0, 0, worldWidth, worldHeight);
        bucketSprite.draw(spriteBatch); // Sprites have their own draw method
        spriteBatch.draw(backgroundTexture, 0, 0, worldWidth, worldHeight);
        spriteBatch.draw(bucketTexture, 0, 0, 1, 1);
        bucketSprite.setSize(1, 1); // Define the size of the sprite
        spriteBatch.end();
	}



	private void logic() {
		// TODO Auto-generated method stub
		
	}



	private void input() {
		// TODO Auto-generated method stub
		float speed = .25f;

	    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
	        bucketSprite.translateX(speed); // Move the bucket right
	    }
		
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
	        // todo: Do something when the user presses the right arrow
	    }
	}



	@Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}

