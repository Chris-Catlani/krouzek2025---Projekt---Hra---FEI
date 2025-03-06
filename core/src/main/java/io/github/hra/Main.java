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
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private SpriteBatch spriteBatch;
    private FitViewport viewport;
    
    private Texture backgroundTexture;
    private Texture bucketTexture;
    private Texture dropTexture;
    private Sound dropSound;
    private Music music;
    private Sprite bucketSprite;
    
    private Vector2 touchPos;
    Array<Sprite> dropSprites;
    
    Rectangle bucketRectangle;
    Rectangle dropRectangle;

    
    float dropTimer;

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
        bucketSprite.setSize(1, 1); // Define the size of the sprite
        
        touchPos = new Vector2();
        dropSprites = new Array<>();
        
        bucketRectangle = new Rectangle();
        dropRectangle = new Rectangle();
        
        music.setLooping(true);
        music.setVolume(.5f);
        music.play();
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
    	ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();
        
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        
        spriteBatch.draw(backgroundTexture, 0, 0, worldWidth, worldHeight);
        bucketSprite.draw(spriteBatch); // Sprites have their own draw method
        
     // draw each sprite
        for (Sprite dropSprite : dropSprites) {
            dropSprite.draw(spriteBatch);
        }
        
        spriteBatch.end();
	}

    private void createDroplet() {
        // create local variables for convenience
        float dropWidth = 1;
        float dropHeight = 1;
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        
        // create the drop sprite
        Sprite dropSprite = new Sprite(dropTexture);
        dropSprite.setSize(dropWidth, dropHeight);
        dropSprite.setX(MathUtils.random(0f, worldWidth - dropWidth));
        dropSprite.setY(worldHeight);
        dropSprites.add(dropSprite); // Add it to the list
    }

    @Override
    public void pause() {

    }

	private void logic() {
		 // Store the worldWidth and worldHeight as local variables for brevity
	    float worldWidth = viewport.getWorldWidth();
	    float worldHeight = viewport.getWorldHeight();

	    // Clamp x to values between 0 and worldWidth
	    bucketSprite.setX(MathUtils.clamp(bucketSprite.getX(), 0, worldWidth));
	    
	 // Store the bucket size for brevity
	    float bucketWidth = bucketSprite.getWidth();
	    float bucketHeight = bucketSprite.getHeight();

	    // Subtract the bucket width
	    bucketSprite.setX(MathUtils.clamp(bucketSprite.getX(), 0, worldWidth - bucketWidth));

	    float delta = Gdx.graphics.getDeltaTime(); // retrieve the current delta
	 // Apply the bucket position and size to the bucketRectangle
	    bucketRectangle.set(bucketSprite.getX(), bucketSprite.getY(), bucketWidth, bucketHeight);
	
        
	 // Loop through the sprites backwards to prevent out of bounds errors
	    for (int i = dropSprites.size - 1; i >= 0; i--) {
	        Sprite dropSprite = dropSprites.get(i); // Get the sprite from the list
	        float dropWidth = dropSprite.getWidth();
	        float dropHeight = dropSprite.getHeight();

	        // Apply the drop position and size to the dropRectangle
	        dropRectangle.set(dropSprite.getX(), dropSprite.getY(), dropWidth, dropHeight);
	        
	        dropSprite.translateY(-2f * delta);

	        // if the top of the drop goes below the bottom of the view, remove it
	        if (dropSprite.getY() < -dropHeight) dropSprites.removeIndex(i);
	        else if (bucketRectangle.overlaps(dropRectangle)) { // Check if the bucket overlaps the drop
	            dropSprites.removeIndex(i); // Remove the drop
	            dropSound.play(); // Play the sound
	        }
	    }
	    
	    dropTimer += delta; // Adds the current delta to the timer
	    if (dropTimer > 1f) { // Check if it has been more than a second
	        dropTimer = 0; // Reset the timer
	        createDroplet(); // Create the droplet
	    }
	}

	private void input() {
		float speed = 4f;
		float delta = Gdx.graphics.getDeltaTime(); // retrieve the current delta
		
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			bucketSprite.translateX(speed * delta); 
	    } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
	        bucketSprite.translateX(-speed * delta); // move the bucket left
	    }
		if (Gdx.input.isTouched()) { // If the user has clicked or tapped the screen
			touchPos.set(Gdx.input.getX(), Gdx.input.getY()); // Get where the touch happened on screen
	        viewport.unproject(touchPos); // Convert the units to the world units of the viewport
	        bucketSprite.setCenterX(touchPos.x); // Change the horizontally centered position of the bucket
		}
	}

	@Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}

