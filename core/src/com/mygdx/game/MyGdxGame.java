package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screen.LoadingScreen;
import com.mygdx.game.screen.SplashScreen;

public class MyGdxGame extends Game {

	public static final String Title = "Slide";
	public static final float VERSION = 0.1f;
	public static final int V_WIDTH = 480;
	public static final int V_HEIGHT = 420;

	public OrthographicCamera camera;
	public SpriteBatch batch;

	public BitmapFont font;

	//AssetManager 사용하여 로딩창 구현
	public AssetManager assets;

	@Override
	public void create(){
		assets=new AssetManager();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, V_WIDTH, V_HEIGHT);//카메라
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.BLACK);

		this.setScreen(new LoadingScreen(this));//게임 첫 화면 LoadingScreen으로 설정
	}

	@Override
	public void render(){
		super.render();
	}

	@Override
	public void dispose(){
		batch.dispose();
		font.dispose();
		assets.dispose();
		this.getScreen().dispose();//실행 후 게임 전체의 쓰레기값들 정리함? 나중에 얘 없어서 오류 날 수 있으니 확인요망!
	}

}