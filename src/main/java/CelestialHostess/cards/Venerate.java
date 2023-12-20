package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.powers.LightChargePower;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.cards.purple.Sanctity;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;


public class Venerate extends AbstractEasyCard {
    public final static String ID = makeID(Venerate.class.getSimpleName());

    public Venerate() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 7;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.applyToSelf(new LightChargePower(p, magicNumber));
    }

    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public String cardArtCopy() {
        return Sanctity.ID;
    }
}