package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screen.LoadingScreen;
import com.mygdx.game.screen.MainMenuScreen;
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

	public LoadingScreen  loadingScreen;
	public SplashScreen splashScreen;
	public MainMenuScreen mainMenuScreen;

	@Override
	public void create(){
		assets=new AssetManager();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, V_WIDTH, V_HEIGHT);//카메라
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.BLACK);

		loadingScreen = new LoadingScreen(this);
		splashScreen = new SplashScreen(this);
		mainMenuScreen = new MainMenuScreen(this);

		this.setScreen(loadingScreen);//게임 첫 화면 LoadingScreen으로 설정
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
		loadingScreen.dispose();
		splashScreen.dispose();
		mainMenuScreen.dispose();//불러들인 화면의 객체들의 사용한 값들을 처리.
	}

}