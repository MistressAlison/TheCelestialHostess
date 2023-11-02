package CelestialHostess.powers;

import CelestialHostess.MainModfile;
import CelestialHostess.patches.BattleCleanupManager;
import basemod.BaseMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class BurdenBreakerPower extends AbstractPower {
    public static final String POWER_ID = MainModfile.makeID(BurdenBreakerPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private final ArrayList<BattleCleanupManager.CleanupLogic> cleanup = new ArrayList<>();

    public BurdenBreakerPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.loadRegion("draw");
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        BaseMod.MAX_HAND_SIZE += amount;
        cleanup.add(BattleCleanupManager.addLogic(() -> BaseMod.MAX_HAND_SIZE -= amount));
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        BaseMod.MAX_HAND_SIZE += stackAmount;
        cleanup.add(BattleCleanupManager.addLogic(() -> BaseMod.MAX_HAND_SIZE -= stackAmount));
    }

    @Override
    public void reducePower(int reduceAmount) {
        super.reducePower(reduceAmount);
        BaseMod.MAX_HAND_SIZE -= reduceAmount;
        cleanup.add(BattleCleanupManager.addLogic(() -> BaseMod.MAX_HAND_SIZE += reduceAmount));
    }

    @Override
    public void onRemove() {
        super.onRemove();
        cleanup.forEach(BattleCleanupManager::runCleanup);
    }
}