package CelestialHostess.actions;

import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;

public class HolyAction extends DoIfAction {
    public HolyAction(Runnable runnable) {
        super(() -> Wiz.adp().hand.group.stream().anyMatch(c -> c instanceof Miracle), runnable);
    }
}
