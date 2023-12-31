package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.patches.CustomTags;
import CelestialHostess.powers.WindChargePower;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.cards.green.Blur;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class GaleForce extends AbstractEasyCard {
    public final static String ID = makeID(GaleForce.class.getSimpleName());

    public GaleForce() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 7;
        baseMagicNumber = magicNumber = 2;
        tags.add(CustomTags.HOSTESS_GIVES_CHARGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        //Wiz.makeInHand(new GaleBlessing(), magicNumber);
        Wiz.applyToSelf(new WindChargePower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public String cardArtCopy() {
        return Blur.ID;
    }
}