package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.util.Wiz;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.purple.Tranquility;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class Serenity extends AbstractEasyCard {
    public final static String ID = makeID(Serenity.class.getSimpleName());

    public Serenity() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MoveCardsAction(Wiz.adp().hand, Wiz.adp().discardPile, magicNumber, cards -> {
            for (AbstractCard card : cards) {
                card.current_x = CardGroup.DISCARD_PILE_X;
                card.current_y = CardGroup.DISCARD_PILE_Y;
                if (card.canUpgrade()) {
                    card.upgrade();
                }
            }
        }));
    }

    @Override
    public void upp() {
        //upgradeBaseCost(0);
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Tranquility.ID;
    }
}