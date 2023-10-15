package CelestialHostess.damageMods;

import CelestialHostess.cards.abstracts.AbstractAbilityCard;
import CelestialHostess.util.Wiz;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class AbilityDamage extends AbstractDamageModifier {

    @Override
    public void onLastDamageTakenUpdate(DamageInfo info, int lastDamageTaken, int overkillAmount, AbstractCreature target) {
        if (DamageModifierManager.getInstigator(info) instanceof AbstractAbilityCard) {
            AbstractAbilityCard aac = (AbstractAbilityCard) DamageModifierManager.getInstigator(info);
            aac.addXP(lastDamageTaken);
            for (AbstractCard c : Wiz.adp().masterDeck.group) {
                if (c.uuid == aac.uuid && c instanceof AbstractAbilityCard) {
                    ((AbstractAbilityCard) c).addXP(lastDamageTaken);
                }
            }
        }
    }

    @Override
    public boolean isInherent() {
        return true;
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        return new AbilityDamage();
    }
}
