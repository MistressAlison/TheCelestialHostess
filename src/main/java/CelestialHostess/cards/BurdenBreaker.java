package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.powers.BurdenBreakerPower;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.cards.green.Expertise;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class BurdenBreaker extends AbstractEasyCard {
    public final static String ID = makeID(BurdenBreaker.class.getSimpleName());

    public BurdenBreaker() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new BurdenBreakerPower(p, magicNumber));
    }

    @Override
    public void upp() {
        isInnate = true;
        uDesc();
    }

    @Override
    public String cardArtCopy() {
        return Expertise.ID;
    }
}