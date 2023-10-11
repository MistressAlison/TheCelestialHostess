package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.cards.interfaces.GlowAdjacentCard;
import CelestialHostess.util.Wiz;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.UpgradeSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Sentinel;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class Bunker extends AbstractEasyCard implements GlowAdjacentCard {
    public final static String ID = makeID(Bunker.class.getSimpleName());
    private static final Color glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();

    public Bunker() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 5;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        for (AbstractCard c : Wiz.getAdjacentCards(this)) {
            addToBot(new UpgradeSpecificCardAction(c));
        }
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public String cardArtCopy() {
        return Sentinel.ID;
    }

    @Override
    public Color getGlowColor(AbstractCard card) {
        return glowColor;
    }

    @Override
    public boolean glowAdjacent(AbstractCard card) {
        return GlowAdjacentCard.super.glowAdjacent(card) && card.canUpgrade();
    }
}