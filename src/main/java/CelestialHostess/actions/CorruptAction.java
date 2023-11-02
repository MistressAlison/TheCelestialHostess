package CelestialHostess.actions;

import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;

public class CorruptAction extends DoAction {
    public CorruptAction() {
        super(() -> {
            for (AbstractCard c : Wiz.adp().hand.group) {
                if (c instanceof Miracle) {
                    Wiz.att(new ExhaustSpecificCardAction(c, Wiz.adp().hand));
                    return;
                }
            }
            Wiz.att(new MakeTempCardInDrawPileAction(new VoidCard(), 1, false, true, false));
        });
    }
}
