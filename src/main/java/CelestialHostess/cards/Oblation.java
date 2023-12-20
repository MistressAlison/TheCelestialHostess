package CelestialHostess.cards;

import CelestialHostess.cardmods.TributeMod;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.purple.Prostrate;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;


public class Oblation extends AbstractEasyCard {
    public final static String ID = makeID(Oblation.class.getSimpleName());

    public Oblation() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
        cardsToPreview = new Miracle();
        CardModifierManager.addModifier(this, new TributeMod(1));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //addToBot(new TributeAction(() -> Wiz.makeInHand(new Miracle(), magicNumber)));
        Wiz.makeInHand(new Miracle(), magicNumber);
    }

    public void upp() {
        exhaust = false;
        uDesc();
    }

    @Override
    public String cardArtCopy() {
        return Prostrate.ID;
    }
}