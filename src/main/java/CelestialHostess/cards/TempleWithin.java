package CelestialHostess.cards;

import CelestialHostess.actions.CleansePowerAction;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.cards.purple.EmptyBody;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static CelestialHostess.MainModfile.makeID;


public class TempleWithin extends AbstractEasyCard {
    public final static String ID = makeID(TempleWithin.class.getSimpleName());

    public TempleWithin() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 11;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new CleansePowerAction(p, magicNumber, pow -> pow.type == AbstractPower.PowerType.DEBUFF));
    }

    public void upp() {
        upgradeBlock(4);
    }

    @Override
    public String cardArtCopy() {
        return EmptyBody.ID;
    }
}