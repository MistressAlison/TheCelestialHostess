package CelestialHostess.cards;

import CelestialHostess.actions.DoAction;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.powers.FireChargePower;
import CelestialHostess.powers.IceChargePower;
import CelestialHostess.powers.LightChargePower;
import CelestialHostess.powers.WindChargePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.red.SearingBlow;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static CelestialHostess.MainModfile.makeID;

public class GlowingStrike extends AbstractEasyCard {
    public final static String ID = makeID(GlowingStrike.class.getSimpleName());

    public GlowingStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 7;
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        addToBot(new DoAction(() -> {
            int cards = 0;
            for (AbstractPower pow : p.powers) {
                if (pow instanceof FireChargePower || pow instanceof IceChargePower || pow instanceof WindChargePower || pow instanceof LightChargePower) {
                    cards++;
                }
            }
            if (cards > 0) {
                addToTop(new DrawCardAction(cards));
            }
        }));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public String cardArtCopy() {
        return SearingBlow.ID;
    }
}