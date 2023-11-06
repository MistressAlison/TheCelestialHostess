package CelestialHostess.cards;

import CelestialHostess.actions.EasyXCostAction;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.cards.tokens.FlameBlessing;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.purple.Eruption;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class FlashPoint extends AbstractEasyCard {
    public final static String ID = makeID(FlashPoint.class.getSimpleName());

    public FlashPoint() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 0;
        cardsToPreview = new FlameBlessing();
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new EasyXCostAction(this, (base, params) -> {
            int effect = base;
            for (int i : params) {
                effect += i;
            }
            if (effect > 0) {
                addToTop(new MakeTempCardInHandAction(new FlameBlessing(), effect));
            }
            return true;
        }, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Eruption.ID;
    }
}