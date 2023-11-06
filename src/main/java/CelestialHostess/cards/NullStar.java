package CelestialHostess.cards;

import CelestialHostess.actions.CorruptAction;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.util.Wiz;
import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.blue.Darkness;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;


public class NullStar extends AbstractEasyCard {
    public final static String ID = makeID(NullStar.class.getSimpleName());

    public NullStar() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FetchAction(Wiz.adp().discardPile, c -> c.type == CardType.ATTACK, magicNumber, l -> {
            for (AbstractCard c : l) {
                c.current_x = CardGroup.DISCARD_PILE_X;
                c.current_y = CardGroup.DISCARD_PILE_Y;
                c.setCostForTurn(0);
            }
        }));
        addToBot(new CorruptAction());
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Darkness.ID;
    }
}