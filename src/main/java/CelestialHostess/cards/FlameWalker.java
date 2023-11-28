package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.powers.FireChargePower;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.red.BurningPact;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class FlameWalker extends AbstractEasyCard {
    public final static String ID = makeID(FlameWalker.class.getSimpleName());

    public FlameWalker() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseSecondMagic = secondMagic = 1;
        baseMagicNumber = magicNumber = 1;
        //cardsToPreview = new FlameBlessing();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(secondMagic));
        //Wiz.makeInHand(new FlameBlessing(), magicNumber);
        Wiz.applyToSelf(new FireChargePower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeSecondMagic(1);
    }

    @Override
    public String cardArtCopy() {
        return BurningPact.ID;
    }
}