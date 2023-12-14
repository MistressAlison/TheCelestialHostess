package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.patches.CustomTags;
import CelestialHostess.powers.FireChargePower;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.cards.green.Survivor;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class Zeal extends AbstractEasyCard {
    public final static String ID = makeID(Zeal.class.getSimpleName());

    public Zeal() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseBlock = block = 3;
        baseMagicNumber = magicNumber = 1;
        tags.add(CustomTags.HOSTESS_GIVES_CHARGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.applyToSelf(new FireChargePower(p, magicNumber));
    }

    @Override
    public void upp() {
        //upgradeBlock(2);
        //upgradeMagicNumber(1);
        upgradeBaseCost(0);
    }

    @Override
    public String cardArtCopy() {
        return Survivor.ID;
    }
}