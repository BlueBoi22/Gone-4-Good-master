package org.cistercian.franco;

import javax.xml.transform.TransformerFactoryConfigurationError;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import org.w3c.dom.Text;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Table.Debug;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import java.awt.*;
import java.io.Console;
import java.util.ArrayList;


public class Gone4good extends ApplicationAdapter {
	SpriteBatch batch;
	OrthographicCamera camera;
	Texture items;
	Texture background;
	Texture bulletpng;
	TextureRegion billHud;
	TextureRegion billHead;
	TextureRegion billStanding;
	TextureRegion zombie;
	TextureRegion bullet;
	TextureRegion baseTile1;
	TextureRegion baseTile2;
	TextureRegion baseTile3;
	TextureRegion baseTile4;
	TextureRegion leftWallTile;
	TextureRegion botWallTile;
	TextureRegion rightWallTile;
	TextureRegion topWallTile;
	TextureRegion botleftcorner;
	TextureRegion botrightcorner;
	TextureRegion topleftcorner;
	TextureRegion toprightcorner;
	Animation billWalking;
	Animation billShootingStart;
	Animation billShootWalk;
	Animation billShootContinue;
	Animation zombieWalk;
	float animationTime = 0;
	int x = 0;
	int y = 0;
	int q = 10;
	boolean walking;
	ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
	ArrayList<Zombie> zombieList = new ArrayList<Zombie>();
	//Color(int rgba808080)

	
	@Override
	public void create () {
		camera = new OrthographicCamera();
    	camera.setToOrtho(false, 1920, 1080);

		batch = new SpriteBatch();
		items = new Texture("Gone 4 Good.png");
		background = new Texture("Backgrounds.png");
		bulletpng = new Texture("Bullet.png");
		billHud = new TextureRegion(items, 0, 0, 184,184);
		billHead = new TextureRegion(new Texture("head.png"));
		billStanding = new TextureRegion(items, 550, 0, 184, 184);
		bullet = new TextureRegion(items, 736, 736+85, 184, 10);
		zombie = new TextureRegion(items, 552, 368, 184, 184);
		zombieWalk = new Animation(0.2f, new TextureRegion(items, 552, 368, 184, 184), new TextureRegion(items, 368, 230, 184, 184));
		billWalking = new Animation(0.2f, new TextureRegion(items, 550, 0, 184, 184), new TextureRegion(items, 736, 0, 184, 184));
		zombie = new TextureRegion(items, 552, 368, 184, 184);
		zombieWalk = new Animation(0.2f, new TextureRegion(items, 552,368,184,184), new TextureRegion(items, 736, 368, 184, 184));
		billShootingStart = new Animation(.02f, new TextureRegion(items, 0, 184, 184, 184), new TextureRegion(items, 184, 184, 184, 184), new TextureRegion(items, 368, 184, 184, 184), new TextureRegion(items, 552, 184, 184, 184));
		billShootContinue = new Animation(.02f, new TextureRegion(items, 368, 184, 184, 184), new TextureRegion(items, 552, 184, 184, 184));
		billShootWalk = new Animation(0.2f, new TextureRegion(items, 736, 184, 184, 184), new TextureRegion(items, 0, 368, 184, 184), new TextureRegion(items, 184, 368, 184, 184), new TextureRegion(items, 184, 552, 184, 184));
		bullet = new TextureRegion(bulletpng, 0, 1, 64, 5);
		
		baseTile1 = new TextureRegion(background, 0, 0, 460, 460);
		baseTile2 = new TextureRegion(background, 460, 0, 460, 460);
		baseTile3 = new TextureRegion(background, 920, 0, 460, 460);
		baseTile4 = new TextureRegion(background, 1380, 0, 460, 460);

		leftWallTile = new TextureRegion(background, 1840, 0, 460, 460);
		botWallTile = new TextureRegion(background, 2300, 0, 460, 460);
		rightWallTile = new TextureRegion(background, 2860, 0, 460, 460);
		topWallTile = new TextureRegion(background, 3220, 0, 460, 460);

		topleftcorner = new TextureRegion(background, 3680, 0, 460, 460);
		toprightcorner = new TextureRegion(background, 4140, 0, 460, 460);
		botleftcorner = new TextureRegion(background, 4600, 0, 460, 460);
		botrightcorner = new TextureRegion(background, 5060, 0, 460, 460);
	}

	
	@Override
	public void render () {
		
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
		}
		animationTime += Gdx.graphics.getDeltaTime();
		Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(touchPos);
		ScreenUtils.clear(1, 0, 0, 1);
		Gdx.gl.glClearColor(.5f, .5f, .5f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		Gdx.graphics.getWidth();
		Gdx.graphics.getHeight();
		
		batch.draw(botleftcorner, 0, 0);
		batch.draw(leftWallTile, 0, 0 + 420);
		batch.draw(botWallTile, 0, 0 + 840);
		batch.draw(leftWallTile, 0, 0 + 840);
		batch.draw(leftWallTile, 0, 0 + 420);
		batch.draw(leftWallTile, 0, 0 + 1260);
		batch.draw(topleftcorner, 0, 0 + 1680);
		batch.draw(botWallTile, 0, 0 + 840);
		camera.position.set(new Vector2(x, y), 0);
		System.out.println(camera.position);
		if (Gdx.input.isKeyPressed(Input.Keys.W)){
			y -= q;
			walking = true;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.S)){
			y += q;
			walking = true;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.A)){
			x += q;
			walking = true;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.D)){
			x -= q;
			walking = true;
		}
		else {
			walking = false;
		}
		if (walking){
		batch.draw(billWalking.getKeyFrame(animationTime, Animation.ANIMATION_LOOPING), 875, 450);
		}
		else if (!walking){
		batch.draw(billStanding, 875, 450);
		}

		batch.draw(billHud, 0, 0);
		float angle = MathUtils.atan2( (touchPos.y - 602), (touchPos.x  - 895));
		float degrees = (float) (180.0 * angle / Math.PI);
		batch.draw(billHead, 900, 605, 20, 0, 32, 40, 1, 1, degrees);

		float angle2 = MathUtils.atan2( (touchPos.y - 450), (touchPos.x  - 875));
		float degrees2 = (float) (180.0 * angle2 / Math.PI);
		if(Gdx.input.justTouched()){
			bulletList.add(new Bullet(950, 590, touchPos.x - 950, touchPos.y - 590));
		}

		for(Bullet i : bulletList){
			i.update();
			batch.draw(bullet, i.x, i.y);
		}
		
		for(Zombie t : zombieList){
			t.update();
			//batch.draw(
		}
		batch.draw(zombie, 300, 400);
		batch.draw(zombieWalk.getKeyFrame(animationTime, Animation.ANIMATION_LOOPING), 384, 450);
		
		batch.end();
	}
	@Override
	public void dispose () {
		batch.dispose();
		items.dispose();
		background.dispose();
	}
}