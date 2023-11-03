package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.powers.PietyPower;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.purple.EmptyFist;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class RighteousBlow extends AbstractEasyCard {
    public final static String ID = makeID(RighteousBlow.class.getSimpleName());

    public RighteousBlow() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 12;
        baseMagicNumber = magicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        Wiz.applyToSelf(new PietyPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeDamage(4);
        //upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return EmptyFist.ID;
    }
}