package CelestialHostess.cards;

import CelestialHostess.actions.EasyXCostAction;
import CelestialHostess.actions.HolyAction;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.patches.CustomTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.green.Skewer;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class PiercingArrow extends AbstractEasyCard {
    public final static String ID = makeID(PiercingArrow.class.getSimpleName());

    public PiercingArrow() {
        super(ID, -1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 4;
        tags.add(CustomTags.HOSTESS_HOLY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HolyAction(() -> addToTop(new EasyXCostAction(this, (base, params) -> {
            int effect = base;
            for (int i : params) {
                effect += i;
            }
            for (int i = 0 ; i < effect ; i++) {
                addToTop(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE, true));
            }
            return true;
        }))));
        //addToBot(new HolyAction(() -> Wiz.att(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE, true))));
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