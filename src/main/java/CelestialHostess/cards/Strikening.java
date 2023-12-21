package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.powers.StrikeningPower;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.cards.blue.StaticDischarge;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class Strikening extends AbstractEasyCard {
    public final static String ID = makeID(Strikening.class.getSimpleName());

    public Strikening() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        baseSecondMagic = secondMagic = 4;
        cardsToPreview = new Strike();
        cardsToPreview.cost = cardsToPreview.costForTurn = 0;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Strike strike = new Strike();
        strike.cost = strike.costForTurn = 0;
        strike.isCostModified = true;
        Wiz.makeInHand(strike, magicNumber);
        Wiz.applyToSelf(new StrikeningPower(p, secondMagic));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return StaticDischarge.ID;
    }
}