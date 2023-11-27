package CelestialHostess.cards.abstracts;

import CelestialHostess.util.Wiz;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.SanctityEffect;

public abstract class AbstractEmpowerCard extends AbstractEasyCard {
    public AbstractEmpowerCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target);
    }

    public AbstractEmpowerCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color);
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (Wiz.adp().hand.group.stream().anyMatch(c -> c instanceof Miracle)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void onRightClick() {
        for (AbstractCard c : Wiz.adp().hand.group) {
            if (c instanceof Miracle) {
                addToTop(new ExhaustSpecificCardAction(c, Wiz.adp().hand));
                addToTop(new VFXAction(new SanctityEffect(Wiz.adp().hb.cX, Wiz.adp().hb.cY)));
                addToTop(new SFXAction("HEAL_1"));
                addToTop(new VFXAction(new BorderFlashEffect(Color.GOLD, true), 0.1F));
                superFlash();
                onEmpower();
                return;
            }
        }
    }

    public abstract void onEmpower();
}
