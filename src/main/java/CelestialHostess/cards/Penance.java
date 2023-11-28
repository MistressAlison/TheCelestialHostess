package CelestialHostess.cards;

import CelestialHostess.actions.BetterSelectCardsInHandAction;
import CelestialHostess.cardmods.TributeMod;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.WreathOfFlame;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;


public class Penance extends AbstractEasyCard {
    public final static String ID = makeID(Penance.class.getSimpleName());

    public Penance() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BetterSelectCardsInHandAction(1, cardStrings.EXTENDED_DESCRIPTION[0], true, true, c -> c.cost >= 0, l -> {
            for (AbstractCard c : l) {
                CardModifierManager.addModifier(c, new TributeMod(1));
                c.cost = 1;
                c.costForTurn = 1;
                c.isCostModified = true;
                c.superFlash(Color.GOLD.cpy());
            }
        }));
    }

    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    public String cardArtCopy() {
        return WreathOfFlame.ID;
    }
}