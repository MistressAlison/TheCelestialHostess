package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractAbilityCard;
import CelestialHostess.util.Wiz;
import CelestialHostess.vfx.AngledCleaveEffect;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.red.Cleave;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class CrossCut extends AbstractAbilityCard {
    public final static String ID = makeID(CrossCut.class.getSimpleName());

    public CrossCut() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = damage = 4;
        isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new SFXAction("ORB_DARK_EVOKE", 0.1f));
        Wiz.atb(new SFXAction("ATTACK_HEAVY"));
        Wiz.atb(new VFXAction(p, new AngledCleaveEffect(MathUtils.random(43.0F, 47.0F)), 0.2F));
        Wiz.atb(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.NONE, true));

        Wiz.atb(new SFXAction("ORB_DARK_EVOKE", 0.1f));
        Wiz.atb(new SFXAction("ATTACK_HEAVY"));
        Wiz.atb(new VFXAction(p, new AngledCleaveEffect(MathUtils.random(-47.0F, -43.0F)), 0.2F));
        Wiz.atb(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void upp() {
        upgradeDamage(1);
    }

    @Override
    public String cardArtCopy() {
        return Cleave.ID;
    }
}