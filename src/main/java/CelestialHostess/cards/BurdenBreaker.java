package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.cards.green.Prepared;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class BurdenBreaker extends AbstractEasyCard {
    public final static String ID = makeID(BurdenBreaker.class.getSimpleName());

    public BurdenBreaker() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new CelestialHostess.powers.BurdenBreakerPower(p, magicNumber));
    }

    @Override
    public void upp() {
        //upgradeBlock(3);
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Prepared.ID;
    }
}