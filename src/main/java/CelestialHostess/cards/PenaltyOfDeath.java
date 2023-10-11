package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.purple.Judgement;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.EndTurnDeathPower;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import static CelestialHostess.MainModfile.makeID;

public class PenaltyOfDeath extends AbstractEasyCard {
    public final static String ID = makeID(PenaltyOfDeath.class.getSimpleName());

    public PenaltyOfDeath() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL);
        baseDamage = damage = 30;
        isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.forAllMonstersLiving(mon -> addToBot(new VFXAction(new WeightyImpactEffect(mon.hb.cX, mon.hb.cY))));
        allDmg(AbstractGameAction.AttackEffect.NONE);
        Wiz.applyToSelf(new EndTurnDeathPower(p));
    }

    @Override
    public void upp() {
        upgradeDamage(10);
    }

    @Override
    public String cardArtCopy() {
        return Judgement.ID;
    }
}