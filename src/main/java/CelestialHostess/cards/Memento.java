package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.purple.JustLucky;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class Memento extends AbstractEasyCard {
    public final static String ID = makeID(Memento.class.getSimpleName());

    public Memento() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 5;
        baseMagicNumber = magicNumber = 2;
        cardsToPreview = new Miracle();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void triggerOnExhaust() {
        addToBot(new MakeTempCardInHandAction(new Miracle(), magicNumber));
    }

    @Override
    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return JustLucky.ID;
    }
}