package CelestialHostess.cards;

import CelestialHostess.actions.DamageFollowupAction;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.powers.LightChargePower;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.green.Dash;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class BreakThrough extends AbstractEasyCard {
    public final static String ID = makeID(BreakThrough.class.getSimpleName());

    public BreakThrough() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 9;
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageFollowupAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY, false, mon -> {
            if (mon.lastDamageTaken > 0) {
                Wiz.applyToSelfTop(new LightChargePower(p, magicNumber));
            }
        }));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public String cardArtCopy() {
        return Dash.ID;
    }
}