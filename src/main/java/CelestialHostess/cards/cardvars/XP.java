package CelestialHostess.cards.cardvars;

import CelestialHostess.MainModfile;
import CelestialHostess.cards.abstracts.AbstractAbilityCard;
import basemod.abstracts.DynamicVariable;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;

public class XP extends DynamicVariable {

    @Override
    public String key() {
        return MainModfile.makeID("XP");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return false;
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof AbstractAbilityCard) {
            return ((AbstractAbilityCard) card).currentXP;
        }
        return -1;
    }

    public void setIsModified(AbstractCard card, boolean v) {}

    @Override
    public int baseValue(AbstractCard card) {
        return value(card);
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return false;
    }

    @Override
    public Color getNormalColor() {
        return Settings.CREAM_COLOR;
    }

    @Override
    public Color getUpgradedColor() {
        return Settings.CREAM_COLOR;
    }

    @Override
    public Color getIncreasedValueColor() {
        return Settings.CREAM_COLOR;
    }

    @Override
    public Color getDecreasedValueColor() {
        return Settings.CREAM_COLOR;
    }
}