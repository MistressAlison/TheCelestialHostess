package CelestialHostess.actions;

import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;

public class HolyAction extends DoAction {
    public HolyAction(Runnable runnable) {
        super(() -> {
            runnable.run();
            for (AbstractCard c : Wiz.adp().hand.group) {
                if (c instanceof Miracle) {
                    runnable.run();
                }
            }
        });
    }
}
