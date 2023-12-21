package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.powers.ChargePreservationPower;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.red.SeverSoul;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class ImbuedStrike extends AbstractEasyCard {
    public final static String ID = makeID(ImbuedStrike.class.getSimpleName());

    public ImbuedStrike() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 12;
        baseMagicNumber = magicNumber = 1;
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        Wiz.applyToSelf(new ChargePreservationPower(p, magicNumber));
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