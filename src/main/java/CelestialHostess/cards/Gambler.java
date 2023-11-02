package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.powers.GamblerPower;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.cards.green.CalculatedGamble;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class Gambler extends AbstractEasyCard {
    public final static String ID = makeID(Gambler.class.getSimpleName());

    public Gambler() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new GamblerPower(p, magicNumber));
    }

    @Override
    public void upp() {
        //upgradeBlock(3);
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return CalculatedGamble.ID;
    }
}