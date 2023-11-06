package CelestialHostess.cards;

import CelestialHostess.actions.BetterSelectCardsInHandAction;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.powers.CardToHandPower;
import CelestialHostess.ui.MicroverseProcessor;
import CelestialHostess.util.Wiz;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.unique.DualWieldAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.Blasphemy;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class Microcosm extends AbstractEasyCard {
    public final static String ID = makeID(Microcosm.class.getSimpleName());

    public Microcosm() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
        if (CardLibrary.getAllCards() != null && !CardLibrary.getAllCards().isEmpty()) {
            portrait = MicroverseProcessor.makeArt();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BetterSelectCardsInHandAction(p.hand.size(), DualWieldAction.TEXT[0], true, true, c -> true, l -> {
            for (AbstractCard c : l) {
                Wiz.applyToSelf(new CardToHandPower(p, 1, c.makeSameInstanceOf()));
            }
        }));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    public String cardArtCopy() {
        return Blasphemy.ID;
    }

    @Override
    public void update() {
        super.update();
        this.portrait = MicroverseProcessor.makeArt();
    }

    @Override
    protected Texture getPortraitImage() {
        return MicroverseProcessor.makeSCVArt();
    }

    @Override
    public boolean transientArt() {
        return true;
    }
}