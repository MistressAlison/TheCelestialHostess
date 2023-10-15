package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractAbilityCard;
import CelestialHostess.damageMods.PiercingDamage;
import CelestialHostess.util.Wiz;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.Bludgeon;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import static CelestialHostess.MainModfile.makeID;

public class GravityHammer extends AbstractAbilityCard {
    public final static String ID = makeID(GravityHammer.class.getSimpleName());

    public GravityHammer() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 13;
        DamageModifierManager.addModifier(this, new PiercingDamage());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
            Wiz.forAdjacentMonsters(m, mon -> addToBot(new VFXAction(new WeightyImpactEffect(mon.hb.cX, mon.hb.cY))));
        }
        addToBot(new WaitAction(0.8F));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        if (m != null) {
            Wiz.forAdjacentMonsters(m, mon -> addToBot(new DamageAction(mon, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE)));
        }
    }

    @Override
    public void upp() {
        upgradeDamage(4);
    }

    @Override
    public String cardArtCopy() {
        return Bludgeon.ID;
    }
}