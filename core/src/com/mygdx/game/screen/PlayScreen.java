package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.actors.SlideButton;

public class PlayScreen implements Screen {

    //App reference
    private MyGdxGame app;

    //Stage var
    private Stage stage;
    private Skin skin;

    //Game Grid
    private int boardSize = 4;
    private int holX, holY;
    private SlideButton[][] buttonGrid;

    //TextButton
    private TextButton buttonBack;


    public PlayScreen(final MyGdxGame app){
        this.app = app;
        this.stage = new Stage(new StretchViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT, app.camera));
    }

    //Initialize the game grid
    private void initGrid() {
        holX = 3;
        holY = 3;

        buttonGrid = new SlideButton[boardSize][boardSize];
        for(int i=0; i < boardSize; i++){
            for(int j=0; j < boardSize; j++){
                if(i != holY || j != holX) {
                    int id = j + 1 + (i * boardSize);
                    buttonGrid[i][j] = new SlideButton(id + "", skin, "default", id);
                    buttonGrid[i][j].setPosition((app.camera.viewportWidth / 7) * 2 + 51 * j,
                            (app.camera.viewportHeight / 5) * 3 - 51 * i);
                    buttonGrid[i][j].setSize(50, 50);

                    buttonGrid[i][j].addAction(Actions.sequence(Actions.alpha(0), Actions.delay(id / 15f),
                            Actions.parallel(
                                    Actions.fadeIn(.5f), Actions.moveBy(0, 10, .25f, Interpolation.pow5Out)
                            )));

                    stage.addActor(buttonGrid[i][j]);
                }
            }
        }
    }

    //Initialize the go to back button
    private void initNavigationButton() {
        buttonBack = new TextButton("Back", skin, "default");
        buttonBack.setPosition(20, app.camera.viewportHeight - 60);
        buttonBack.setSize(100,50);
        buttonBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.mainMenuScreen);
            }
        });

        stage.addActor(buttonBack);
    }

    @Override
    public void show() {
        System.out.println("PlayScreen");
        Gdx.input.setInputProcessor(stage);
        this.stage.clear();

        this.skin = new Skin();
        this.skin.addRegions(app.assets.get("ui/uiskin.atlas", TextureAtlas.class));//skin객체에 디자인 넣기. 매개변수 atlas 파일
        this.skin.add("default-font", app.font24);//skin 객체에 폰트 설정. uiskin.json에서 설정되어있는 default-font로 font24객체를 넣어줌.
        this.skin.load(Gdx.files.internal("ui/uiskin.json"));//마지막에 설정된 값과 json파일 skin객체로 로드.

        initNavigationButton();
        initGrid();
    }

    private void update(float delta){
        stage.act(delta);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);//update 메서드에서 stage객체에 요소 추가
        stage.draw();//stage객체를 사용자에게 보여주는 역할 수행. 초중요 @@@@@@@

        app.batch.begin();
        app.font24.draw(app.batch, "Screen: Play", 20, 20);
        app.batch.end();
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

    }
}
