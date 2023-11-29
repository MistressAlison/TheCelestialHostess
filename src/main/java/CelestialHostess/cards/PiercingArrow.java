package CelestialHostess.cards;

import CelestialHostess.actions.EasyXCostAction;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.damageMods.PiercingDamage;
import CelestialHostess.patches.CustomTags;
import CelestialHostess.util.Wiz;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.green.Skewer;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Collections;

import static CelestialHostess.MainModfile.makeID;

public class PiercingArrow extends AbstractEasyCard {
    public final static String ID = makeID(PiercingArrow.class.getSimpleName());
    public PiercingArrow() {
        super(ID, -1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 5;
        tags.add(CustomTags.HOSTESS_IF_MIRACLE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new EasyXCostAction(this, (base, params) -> {
            int effect = base;
            for (int i : params) {
                effect += i;
            }
            for (int i = 0 ; i < effect ; i++) {
                DamageInfo info = new DamageInfo(p, damage, damageTypeForTurn);
                if (Wiz.adp().hand.group.stream().anyMatch(c -> c instanceof Miracle)) {
                    DamageModifierManager.bindDamageMods(info, Collections.singletonList(new PiercingDamage()));
                }
                addToTop(new DamageAction(m, info, AbstractGameAction.AttackEffect.FIRE, true));
            }
            return true;
        }));
        //addToBot(new HolyAction(() -> Wiz.att(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE, true))));
    }

    @Override
    public boolean freeToPlay() {
        return super.freeToPlay() || (Wiz.isInCombat() && Wiz.adp().hand.group.stream().anyMatch(c -> c instanceof Miracle));
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (Wiz.adp().hand.group.stream().anyMatch(c -> c instanceof Miracle)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }

    @Override
    public String cardArtCopy() {
        return Skewer.ID;
    }
}