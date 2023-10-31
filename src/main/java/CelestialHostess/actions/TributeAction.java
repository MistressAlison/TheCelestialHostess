package CelestialHostess.actions;

import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.List;
import java.util.function.Consumer;

public class TributeAction extends BetterSelectCardsInHandAction {
    public TributeAction(Runnable doIf) {
        super(1, ExhaustAction.TEXT[0], false, false, c -> true, l -> {
            for (AbstractCard c : l) {
                Wiz.adp().hand.moveToExhaustPile(c);
            }
            if (!l.isEmpty()) {
                doIf.run();
            }
            l.clear();
        });
    }
}
