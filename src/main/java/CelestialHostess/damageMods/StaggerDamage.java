package CelestialHostess.damageMods;

import CelestialHostess.powers.StaggerPower;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class StaggerDamage extends AbstractDamageModifier {
    int amount;

    public StaggerDamage(int amount) {
        this.amount = amount;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        addToTop(new ApplyPowerAction(target, info.owner, new StaggerPower(target, amount)));
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        return new StaggerDamage(amount);
    }
}
