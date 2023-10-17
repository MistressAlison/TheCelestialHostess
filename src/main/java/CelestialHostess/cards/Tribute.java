package CelestialHostess.cards;

import CelestialHostess.actions.BetterSelectCardsInHandAction;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.Prostrate;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;


public class Tribute extends AbstractEasyCard {
    public final static String ID = makeID(Tribute.class.getSimpleName());

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.NONE;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;

    private static final int COST = 0;
    private static final int EFFECT = 1;
    private static final int UP_EFFECT = 1;

    public Tribute() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = EFFECT;
        exhaust = true;
        cardsToPreview = new Miracle();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new BetterSelectCardsInHandAction(1, ExhaustAction.TEXT[0], false, false, c -> true, l -> {
            for (AbstractCard c : l) {
                Wiz.adp().hand.moveToExhaustPile(c);
            }
            if (!l.isEmpty()) {
                Wiz.makeInHand(new Miracle(), magicNumber);
            }
            l.clear();
        }));
    }

    public void upp() {
        exhaust = false;
        uDesc();
    }

    @Override
    public String cardArtCopy() {
        return Prostrate.ID;
    }
}