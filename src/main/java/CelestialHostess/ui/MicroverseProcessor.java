package CelestialHostess.ui;

import CelestialHostess.MainModfile;
import CelestialHostess.cards.Microverse;
import CelestialHostess.util.CardArtRoller;
import CelestialHostess.util.ImageHelper;
import CelestialHostess.util.TexLoader;
import basemod.interfaces.ScreenPostProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import static com.badlogic.gdx.graphics.GL20.GL_DST_COLOR;
import static com.badlogic.gdx.graphics.GL20.GL_ZERO;

public class MicroverseProcessor implements ScreenPostProcessor {
    private static final FrameBuffer buffer = ImageHelper.createBuffer(250, 190);
    private static final FrameBuffer SCVBuffer = ImageHelper.createBuffer(500, 380);
    private static final OrthographicCamera og = new OrthographicCamera(250, 190);
    private static final OrthographicCamera SCVog = new OrthographicCamera(500, 380);
    private static final Texture mask = CardArtRoller.getMask(CardLibrary.getCard(Microverse.ID));;
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
        ImageHelper.beginBuffer(buffer);
        sb.begin();

        sb.setBlendFunction(GL20.GL_ONE, GL20.GL_ZERO);
        sb.draw(textureRegion.getTexture(), -125, -95, -125, -95, 250, 190, 1, 1, 0, 0, 0, textureRegion.getTexture().getWidth(), textureRegion.getTexture().getHeight(), false, false);
        sb.setBlendFunction(GL_DST_COLOR, GL_ZERO);
        sb.setColor(Color.WHITE);
        sb.draw(mask, -125, -95, -125, -95, 250, 190, 1, 1, 0, 0, 0, mask.getWidth(), mask.getHeight(), false, true);

        sb.end();
        buffer.end();
        portrait = new TextureAtlas.AtlasRegion(ImageHelper.getBufferTexture(buffer).getTexture(), 0, 0, 250, 190);

        SpriteBatch sb2 = new SpriteBatch();
        sb2.setProjectionMatrix(SCVog.combined);
        ImageHelper.beginBuffer(SCVBuffer);
        sb2.begin();

        sb2.setBlendFunction(GL20.GL_ONE, GL20.GL_ZERO);
        sb2.draw(textureRegion.getTexture(), -250, -190, -250, -190, 500, 380, 1, 1, 0, 0, 0, textureRegion.getTexture().getWidth(), textureRegion.getTexture().getHeight(), false, false);
        sb2.setBlendFunction(GL_DST_COLOR, GL_ZERO);
        sb2.setColor(Color.WHITE);
        sb2.draw(mask, -250, -190, -250, -190, 500, 380, 1, 1, 0, 0, 0, mask.getWidth(), mask.getHeight(), false, true);

        sb2.end();
        SCVBuffer.end();
        scv = ImageHelper.getBufferTexture(SCVBuffer).getTexture();

        spriteBatch.begin();
    }

    public static TextureAtlas.AtlasRegion makeArt() {
        return portrait;
        //return new TextureAtlas.AtlasRegion(portrait, 0, 0, 250, 190);
    }

    public static Texture makeSCVArt() {
        return scv;
    }
}
