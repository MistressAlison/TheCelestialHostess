package CelestialHostess.powers;

import CelestialHostess.MainModfile;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import CelestialHostess.util.TexLoader;

public abstract class AbstractEasyPower extends AbstractPower {
    public AbstractEasyPower(String NAME, PowerType powerType, boolean isTurnBased, AbstractCreature owner, int amount) {
        this.isTurnBased = isTurnBased;

        this.name = NAME;

        this.owner = owner;
        this.amount = amount;
        this.type = powerType;

        Texture normalTexture = TexLoader.getTexture(MainModfile.modID + "Resources/images/powers/" + NAME.replaceAll(" ", "") + "32.png");
        Texture hiDefImage = TexLoader.getTexture(MainModfile.modID + "Resources/images/powers/" + NAME.replaceAll(" ", "") + "84.png");
        if (hiDefImage != null) {
            region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
            if (normalTexture != null)
                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        } else if (normalTexture != null) {
            this.img = normalTexture;
            region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        }

        this.updateDescription();
    }
}