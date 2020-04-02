package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class MyGdxGame extends ApplicationAdapter {
	private OrthographicCamera cam;
	private ShapeRenderer sr;
	private Vector3 pos; //포지션

	private FreeTypeFontGenerator fontGenerator;
	private  FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
	private BitmapFont font;
	private  SpriteBatch batch;
	
	private int taps = 0;

	public void create(){//메인 화면
		sr = new ShapeRenderer();
		cam = new OrthographicCamera();
		batch = new SpriteBatch();//특정 상황에 나타나게하는 객체
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		pos = new Vector3(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() /2,0);

		fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("font/RIDIBatang.otf"));//폰트 적용
		fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontParameter.size = 150;
		fontParameter.borderWidth = 5;
		fontParameter.borderColor = Color.BLACK;
		fontParameter.color = Color.WHITE;
		font = fontGenerator.generateFont(fontParameter);
	}

	public void render(){
		//게임 로직
		cam.update();

		if(Gdx.input.isTouched()){
			pos.set(Gdx.input.getX(), Gdx.input.getY(),0);
			cam.unproject(pos);
			taps++;
		}
		//드로잉
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(cam.combined);

		sr.begin(ShapeRenderer.ShapeType.Filled);
		sr.setColor(Color.GREEN);
		sr.circle(pos.x, pos.y, 64);
		sr.end();

		batch.begin();
		font.draw(batch, "Taps: "+ taps, 50, Gdx.graphics.getHeight() -50);
		batch.end();

	}

	public void dipose(){
		sr.dispose();
		batch.dispose();
	}
}
