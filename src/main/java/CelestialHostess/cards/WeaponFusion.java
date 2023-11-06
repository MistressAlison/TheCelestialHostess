package CelestialHostess.cards;

import CelestialHostess.actions.BetterSelectCardsInHandAction;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.cards.tokens.FlameBlessing;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.ConjureBlade;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;


public class WeaponFusion extends AbstractEasyCard {
    public final static String ID = makeID(WeaponFusion.class.getSimpleName());

    public WeaponFusion() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
        cardsToPreview = new FlameBlessing();
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BetterSelectCardsInHandAction(2, ExhaustAction.TEXT[0], true, true, c -> c.type == CardType.ATTACK, l -> {
            AbstractCard card = new FlameBlessing();
            card.baseDamage = 0;
            for (AbstractCard c : l) {
                Wiz.adp().hand.moveToExhaustPile(c);
                card.baseDamage += c.baseDamage;
            }
            Wiz.makeInHand(card);
            l.clear();
        }));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return ConjureBlade.ID;
    }
}