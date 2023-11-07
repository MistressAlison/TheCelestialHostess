package CelestialHostess.cards;

import CelestialHostess.actions.CorruptAction;
import CelestialHostess.actions.ModifyMagicAction;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.green.RiddleWithHoles;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;

import static CelestialHostess.MainModfile.makeID;

public class RelentlessAssault extends AbstractEasyCard {
    public final static String ID = makeID(RelentlessAssault.class.getSimpleName());

    public RelentlessAssault() {
        super(ID, 0, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 6;
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0 ; i < magicNumber ; i++) {
            switch (MathUtils.random(3)) {
                case 0:
                    if (m != null) {
                        addToBot(new VFXAction(new ThrowDaggerEffect(m.hb.cX, m.hb.cY)));
                    }
                    addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE, true));
                    break;
                case 1:
                    addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
                    break;
                case 2:
                    addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY, true));
                    break;
                case 3:
                    addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL, true));
                    break;
            }
        }
        addToBot(new ModifyMagicAction(uuid, 1));
        addToBot(new CorruptAction());
    }

    @Override
    public void upp() {
        //upgradeBaseCost(1);
        upgradeDamage(2);
        //upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return RiddleWithHoles.ID;
    }
}