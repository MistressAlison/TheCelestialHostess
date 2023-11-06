package CelestialHostess.powers;

import CelestialHostess.MainModfile;
import CelestialHostess.actions.DoAction;
import CelestialHostess.patches.EnergyGainPatch;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ManaMasterPower extends AbstractPower implements EnergyGainPatch.OnGainEnergyPower {
    public static final String POWER_ID = MainModfile.makeID(ManaMasterPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ManaMasterPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.loadRegion("mastery");
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onEnergyRecharge() {
        flash();
        addToTop(new DoAction(() -> EnergyGainPatch.hookActive = true));
        addToTop(new GainEnergyAction(this.amount));
        addToTop(new DoAction(() -> EnergyGainPatch.hookActive = false));
    }

    @Override
    public void onGainEnergy(int amount) {
        flash();
        addToTop(new DoAction(() -> EnergyGainPatch.hookActive = true));
        addToTop(new GainEnergyAction(this.amount));
        addToTop(new DoAction(() -> EnergyGainPatch.hookActive = false));
    }
}