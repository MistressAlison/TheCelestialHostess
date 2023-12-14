package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.patches.CustomTags;
import CelestialHostess.powers.WindChargePower;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.green.Burst;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static CelestialHostess.MainModfile.makeID;

public class Zephyr extends AbstractEasyCard {
    public final static String ID = makeID(Zephyr.class.getSimpleName());

    public Zephyr() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        tags.add(CustomTags.HOSTESS_GIVES_CHARGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //Wiz.applyToSelf(new PietyPower(p, magicNumber));
        Wiz.applyToSelf(new WindChargePower(p, magicNumber));
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, magicNumber)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Burst.ID;
    }
}