package CelestialHostess.cards;

import CelestialHostess.actions.BetterSelectCardsInHandAction;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.DualWield;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class DoubleBladed extends AbstractEasyCard {
    public final static String ID = makeID(DoubleBladed.class.getSimpleName());

    public DoubleBladed() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BetterSelectCardsInHandAction(1, DualWield.TEXT[0], false, true, c -> c.type == CardType.ATTACK, l -> {
            for (AbstractCard c : l) {
                Wiz.makeInHand(c.makeSameInstanceOf(), magicNumber);
            }
        }));
    }

    @Override
    public void upp() {
        //upgradeBlock(3);
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return DualWield.ID;
    }
}