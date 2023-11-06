package CelestialHostess.cards;

import CelestialHostess.actions.CorruptAction;
import CelestialHostess.actions.HolyAction;
import CelestialHostess.actions.ProcessDamageRandomEnemyAction;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.patches.CustomTags;
import CelestialHostess.vfx.BigExplosionVFX;
import CelestialHostess.vfx.SpotlightEnemyEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.Storm;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.InversionBeamEffect;

import static CelestialHostess.MainModfile.makeID;

public class Lightmare extends AbstractEasyCard {
    public final static String ID = makeID(Lightmare.class.getSimpleName());

    public Lightmare() {
        super(ID, 0, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = damage = 15;
        tags.add(CustomTags.HOSTESS_IF_MIRACLE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HolyAction(() -> addToTop(new ProcessDamageRandomEnemyAction(this, (mon, total) -> {
            addToTop(new BigExplosionVFX(mon));
            addToTop(new DamageAction(mon, new DamageInfo(p, total, damageTypeForTurn)));
            addToTop(new VFXAction(new InversionBeamEffect(mon.hb.cX)));
            addToTop(new VFXAction(new SpotlightEnemyEffect(mon)));
        }))));
        addToBot(new CorruptAction());
    }

    @Override
    public void upp() {
        upgradeDamage(5);
    }

    @Override
    public String cardArtCopy() {
        return Storm.ID;
    }
}