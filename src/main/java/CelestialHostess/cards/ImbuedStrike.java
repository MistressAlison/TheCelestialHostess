package CelestialHostess.cards;

import CelestialHostess.actions.DoAction;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.powers.FireChargePower;
import CelestialHostess.powers.IceChargePower;
import CelestialHostess.powers.WindChargePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.red.SeverSoul;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static CelestialHostess.MainModfile.makeID;

public class ImbuedStrike extends AbstractEasyCard {
    public final static String ID = makeID(ImbuedStrike.class.getSimpleName());

    public ImbuedStrike() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 12;
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        addToBot(new DoAction(() -> {
            for (AbstractPower pow : p.powers) {
                if (pow instanceof FireChargePower || pow instanceof IceChargePower || pow instanceof WindChargePower) {
                    int times = pow.amount;
                    if (this.type == CardType.ATTACK && pow instanceof FireChargePower) {
                        times--;
                    }
                    if (this.type == CardType.SKILL && pow instanceof IceChargePower) {
                        times--;
                    }
                    if (this.exhaust && pow instanceof WindChargePower) {
                        times--;
                    }
                    for (int i = 0 ; i < times ; i++) {
                        pow.onSpecificTrigger();
                    }
                }
            }
        }));
    }

    @Override
    public void upp() {
        upgradeDamage(4);
    }

    @Override
    public String cardArtCopy() {
        return SeverSoul.ID;
    }
}