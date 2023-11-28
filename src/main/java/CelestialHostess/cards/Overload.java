package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.powers.FireChargePower;
import CelestialHostess.powers.IceChargePower;
import CelestialHostess.powers.WindChargePower;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.cards.blue.Overclock;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class Overload extends AbstractEasyCard {
    public final static String ID = makeID(Overload.class.getSimpleName());

    public Overload() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 3;
        //exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new FireChargePower(p, magicNumber));
        Wiz.applyToSelf(new IceChargePower(p, magicNumber));
        Wiz.applyToSelf(new WindChargePower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Overclock.ID;
    }
}