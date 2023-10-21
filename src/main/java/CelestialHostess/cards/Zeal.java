package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.green.Survivor;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class Zeal extends AbstractEasyCard {
    public final static String ID = makeID(Zeal.class.getSimpleName());

    public Zeal() {
        super(ID, 0, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseBlock = block = 5;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new DiscardAction(p, p, 1, false));
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public String cardArtCopy() {
        return Survivor.ID;
    }
}