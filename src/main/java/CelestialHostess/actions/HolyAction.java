package CelestialHostess.actions;

import CelestialHostess.util.Wiz;

public class HolyAction extends DoIfAction {
    public HolyAction(Runnable runnable) {
        super(() -> Wiz.auraActive(), runnable);
    }
}
