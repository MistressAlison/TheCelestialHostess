package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.cards.interfaces.GlowAdjacentCard;
import CelestialHostess.patches.CustomTags;
import CelestialHostess.util.Wiz;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Reflex;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static CelestialHostess.MainModfile.makeID;

public class ShieldDrop extends AbstractEasyCard implements GlowAdjacentCard {
    public final static String ID = makeID(ShieldDrop.class.getSimpleName());
    private static final Color glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();

    public ShieldDrop() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseBlock = block = 5;
        tags.add(CustomTags.HOSTESS_GRAB);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        for (AbstractCard c : Wiz.getAdjacentCards(this)) {
            if (c.type == CardType.ATTACK) {
                addToBot(new NewQueueCardAction(c, m, true, true));
            }
        }
    }

    @Override
    public void upp() {
        //upgradeBlock(3);
        upgradeBaseCost(1);
    }

    @Override
    public String cardArtCopy() {
        return Reflex.ID;
    }

    @Override
    public Color getGlowColor(AbstractCard card) {
        return glowColor;
    }

    @Override
    public boolean glowAdjacent(AbstractCard card) {
        checkedCards.clear();
        return chainCheck(this, card);
    }

    private final ArrayList<AbstractCard> checkedCards = new ArrayList<>();
    private boolean chainCheck(AbstractCard currentCard, AbstractCard cardToCheck) {
        if (cardToCheck.type == CardType.ATTACK) {
            if (Wiz.getAdjacentCards(currentCard).contains(cardToCheck)) {
                return true;
            }
            for (AbstractCard c : Wiz.getAdjacentCards(currentCard)) {
                if (c.hasTag(CustomTags.HOSTESS_GRAB) && !checkedCards.contains(c)) {
                    checkedCards.add(c);
                    return chainCheck(c, cardToCheck);
                }
            }
        }
        return false;
    }
}