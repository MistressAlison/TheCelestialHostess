package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.powers.TempleWithinPower;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.cards.purple.EmptyBody;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class TempleWithin extends AbstractEasyCard {
    public final static String ID = makeID(TempleWithin.class.getSimpleName());

    public TempleWithin() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new TempleWithinPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeBaseCost(1);
        //upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return EmptyBody.ID;
    }
}