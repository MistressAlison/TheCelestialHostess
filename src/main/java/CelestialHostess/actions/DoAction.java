package CelestialHostess.actions;

public class DoAction extends DoIfAction {
    public DoAction(Runnable runnable) {
        super(() -> true, runnable);
    }
}
