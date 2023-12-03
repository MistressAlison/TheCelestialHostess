package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.powers.ManaMasterPower;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.cards.purple.WaveOfTheHand;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class ManaMaster extends AbstractEasyCard {
    public final static String ID = makeID(ManaMaster.class.getSimpleName());

    public ManaMaster() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new ManaMasterPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeBaseCost(2);
    }

    @Override
    public String cardArtCopy() {
        return WaveOfTheHand.ID;
    }
}