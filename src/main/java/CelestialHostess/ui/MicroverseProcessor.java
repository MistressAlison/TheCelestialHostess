package CelestialHostess.ui;

import CelestialHostess.cards.Microverse;
import CelestialHostess.util.CardArtRoller;
import CelestialHostess.util.ImageHelper;
import basemod.interfaces.ScreenPostProcessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.BufferUtils;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import java.nio.IntBuffer;

import static com.badlogic.gdx.graphics.GL20.GL_DST_COLOR;
import static com.badlogic.gdx.graphics.GL20.GL_ZERO;

public class MicroverseProcessor implements ScreenPostProcessor {
    private static final FrameBuffer buffer = ImageHelper.createBuffer(250, 190);
    private static final FrameBuffer SCVBuffer = ImageHelper.createBuffer(500, 380);
    private static final OrthographicCamera og = new OrthographicCamera(250, 190);
    private static final OrthographicCamera SCVog = new OrthographicCamera(500, 380);
    private static final Texture mask = CardArtRoller.getMask(CardLibrary.getCard(Microverse.ID));
    private static final int[] saveEquations = new int[]{0, 0};
    public static TextureAtlas.AtlasRegion portrait;
    public static Texture scv;

    @Override
    public void postProcess(SpriteBatch spriteBatch, TextureRegion textureRegion, OrthographicCamera camera) {
        spriteBatch.setColor(Color.WHITE);
        spriteBatch.setBlendFunction(GL20.GL_ONE, GL20.GL_ZERO);
        spriteBatch.draw(textureRegion, 0, 0);
        spriteBatch.end();

        SpriteBatch sb = new SpriteBatch();
        sb.setProjectionMatrix(og.combined);
        prepBuffer(buffer);
        sb.begin();

        sb.setBlendFunction(GL20.GL_ONE, GL20.GL_ZERO);
        sb.draw(textureRegion.getTexture(), -125, -95, -125, -95, 250, 190, 1, 1, 0, 0, 0, textureRegion.getTexture().getWidth(), textureRegion.getTexture().getHeight(), false, false);
        sb.setBlendFunction(GL_DST_COLOR, GL_ZERO);
        sb.setColor(Color.WHITE);
        sb.draw(mask, -125, -95, -125, -95, 250, 190, 1, 1, 0, 0, 0, mask.getWidth(), mask.getHeight(), false, true);

        sb.end();
        sb.dispose();
        endBuffer(buffer);
        portrait = new TextureAtlas.AtlasRegion(ImageHelper.getBufferTexture(buffer).getTexture(), 0, 0, 250, 190);

        SpriteBatch sb2 = new SpriteBatch();
        sb2.setProjectionMatrix(SCVog.combined);
        prepBuffer(SCVBuffer);
        sb2.begin();

        sb2.setBlendFunction(GL20.GL_ONE, GL20.GL_ZERO);
        sb2.draw(textureRegion.getTexture(), -250, -190, -250, -190, 500, 380, 1, 1, 0, 0, 0, textureRegion.getTexture().getWidth(), textureRegion.getTexture().getHeight(), false, false);
        sb2.setBlendFunction(GL_DST_COLOR, GL_ZERO);
        sb2.setColor(Color.WHITE);
        sb2.draw(mask, -250, -190, -250, -190, 500, 380, 1, 1, 0, 0, 0, mask.getWidth(), mask.getHeight(), false, true);

        sb2.end();
        sb2.dispose();
        endBuffer(SCVBuffer);
        scv = ImageHelper.getBufferTexture(SCVBuffer).getTexture();

        spriteBatch.begin();
    }

    public static void prepBuffer(FrameBuffer buffer) {
        buffer.begin();
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        IntBuffer buf_rgb = BufferUtils.newIntBuffer(16);
        IntBuffer buf_a = BufferUtils.newIntBuffer(16);
        Gdx.gl.glGetIntegerv(GL20.GL_BLEND_EQUATION_RGB, buf_rgb);
        Gdx.gl.glGetIntegerv(GL20.GL_BLEND_EQUATION_ALPHA, buf_a);
        saveEquations[0] = buf_rgb.get();
        saveEquations[1] = buf_a.get();
        Gdx.gl.glBlendEquationSeparate(saveEquations[0], GL30.GL_MAX);
    }

    public static void endBuffer(FrameBuffer buffer) {
        buffer.end();
        Gdx.gl.glBlendEquationSeparate(saveEquations[0], saveEquations[1]);
    }

    public static TextureAtlas.AtlasRegion makeArt() {
        return portrait;
    }

    public static Texture makeSCVArt() {
        return scv;
    }
}
