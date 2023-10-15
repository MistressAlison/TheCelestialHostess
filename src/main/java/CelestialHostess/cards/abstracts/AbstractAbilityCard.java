package CelestialHostess.cards.abstracts;

import CelestialHostess.cardmods.XPMod;
import CelestialHostess.damageMods.AbilityDamage;
import CelestialHostess.util.KeywordManager;
import basemod.BaseMod;
import basemod.abstracts.CustomSavable;
import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractAbilityCard extends AbstractEasyCard implements CustomSavable<Integer> {
    private static final List<String> descriptors = new ArrayList<>(Collections.singletonList(BaseMod.getKeywordTitle(KeywordManager.ABILITY)));
    private static final List<TooltipInfo> tip = new ArrayList<>(Collections.singletonList(new TooltipInfo(BaseMod.getKeywordTitle(KeywordManager.ABILITY), BaseMod.getKeywordDescription(KeywordManager.ABILITY))));
    private static boolean copyFix;
    public boolean expUpgrade = false;
    public int totalXP;
    public int currentXP;

    public AbstractAbilityCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target);
        DamageModifierManager.addModifier(this, new AbilityDamage());
        CardModifierManager.addModifier(this, new XPMod());
    }

    public AbstractAbilityCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color);
        DamageModifierManager.addModifier(this, new AbilityDamage());
        CardModifierManager.addModifier(this, new XPMod());
    }

    @Override
    public AbstractCard makeCopy() {
        AbstractCard copy = super.makeCopy();
        copy.timesUpgraded = this.timesUpgraded;
        return copy;
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        copyFix = true;
        AbstractAbilityCard copy = (AbstractAbilityCard) super.makeStatEquivalentCopy();
        copyFix = false;
        copy.totalXP = this.totalXP;
        copy.currentXP = this.currentXP;
        copy.initializeDescription();
        return copy;
    }

    @Override
    public List<String> getCardDescriptors() {
        return descriptors;
    }

    @Override
    public List<TooltipInfo> getCustomTooltipsTop() {
        return tip;
    }

    @Override
    public boolean canUpgrade() {
        return expUpgrade;
    }

    public void upgrade() {
        if (expUpgrade || copyFix) {
            upgradeName();
            upp();
            expUpgrade = false;
        }
    }

    @Override
    protected void upgradeName() {
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = this.originalName + "+" + timesUpgraded;
        this.initializeTitle();
    }

    @Override
    public Type savedType() {
        return new TypeToken<Integer>(){}.getType();
    }

    @Override
    public Integer onSave() {
        return totalXP;
    }

    @Override
    public void onLoad(Integer integer) {
        if (integer != null) {
            addXP(integer);
        }
    }

    public int xpForLevel() {
        return 50 + 50 * timesUpgraded;
    }

    public void addXP(int amount) {
        totalXP += amount;
        currentXP += amount;
        while (currentXP >= xpForLevel()) {
            currentXP -= xpForLevel();
            expUpgrade = true;
            upgrade();
            info = baseInfo = xpForLevel();
        }
        initializeDescription();
    }
}
