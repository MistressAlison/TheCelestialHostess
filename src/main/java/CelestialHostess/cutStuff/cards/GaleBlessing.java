package CelestialHostess.cutStuff.cards;

import CelestialHostess.actions.DoForEachMiracleAction;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.patches.CustomTags;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.green.StormOfSteel;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class GaleBlessing extends AbstractEasyCard {
    public final static String ID = makeID(GaleBlessing.class.getSimpleName());

    public GaleBlessing() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 1;
        selfRetain = true;
        exhaust = true;
        tags.add(CustomTags.HOSTESS_FOR_EACH_MIRACLE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //addToBot(new DrawCardAction(magicNumber));
        addToBot(new DoForEachMiracleAction(() -> Wiz.att(new DrawCardAction(magicNumber))));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return StormOfSteel.ID;
    }
}