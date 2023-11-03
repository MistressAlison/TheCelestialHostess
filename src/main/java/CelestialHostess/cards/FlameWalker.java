package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.cards.tokens.FlameBlessing;
import CelestialHostess.util.KeywordManager;
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
        cardsToPreview = new FlameBlessing();
        addCustomKeyword(KeywordManager.HOLY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(secondMagic));
        Wiz.makeInHand(new FlameBlessing(), magicNumber);
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