package CelestialHostess.cards;

import CelestialHostess.actions.TributeAction;
import CelestialHostess.cardmods.FlatBlockMod;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Armaments;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class GearRepair extends AbstractEasyCard {
    public final static String ID = makeID(GearRepair.class.getSimpleName());

    public GearRepair() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new TributeAction(() -> {
            for (AbstractCard c : Wiz.adp().hand.group) {
                if (c.baseBlock > 0) {
                    c.superFlash(Color.GOLD.cpy());
                    CardModifierManager.addModifier(c, new FlatBlockMod(magicNumber));
                }
            }
        }));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }

    @Override
    public String cardArtCopy() {
        return Armaments.ID;
    }
}