package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.MyGdxGame;

public class MainMenuScreen implements Screen {

    private final MyGdxGame app;
    private Stage stage;
    private ShapeRenderer shapeRenderer;

    public MainMenuScreen(final MyGdxGame app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT, app.camera));
        this.shapeRenderer = new ShapeRenderer();
    }


    @Override
    public void show() {
        System.out.println("MainMenu-Screen.show");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        app.batch.begin();
        app.font.draw(app.batch, "Screen Main Menu", 20, 20);
        app.batch.end();
    }

    private void update(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        shapeRenderer.dispose();
    }
}
