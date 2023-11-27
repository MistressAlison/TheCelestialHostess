package CelestialHostess.cutStuff.cards;

import CelestialHostess.actions.DoForEachMiracleAction;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.patches.CustomTags;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.red.SearingBlow;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class FlameBlessing extends AbstractEasyCard {
    public final static String ID = makeID(FlameBlessing.class.getSimpleName());

    public FlameBlessing() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY, CardColor.COLORLESS);
        baseDamage = damage = 3;
        isMultiDamage = true;
        selfRetain = true;
        exhaust = true;
        tags.add(CustomTags.HOSTESS_FOR_EACH_MIRACLE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE, true));
        addToBot(new DoForEachMiracleAction(() -> Wiz.att(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE, true))));
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }

    @Override
    public String cardArtCopy() {
        return SearingBlow.ID;
    }
}