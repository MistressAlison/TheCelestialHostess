package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEmpowerCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.green.PiercingWail;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.IntimidateEffect;

import static CelestialHostess.MainModfile.makeID;

public class EternalVoice extends AbstractEmpowerCard {
    public final static String ID = makeID(EternalVoice.class.getSimpleName());

    public EternalVoice() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = damage = 3;
        baseMagicNumber = magicNumber = 1;
        isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("ATTACK_PIERCING_WAIL"));
        addToBot(new VFXAction(p, new IntimidateEffect(p.hb.cX, p.hb.cY), 1.0F));
        for (int i = 0 ; i < magicNumber ; i++) {
            allDmg(AbstractGameAction.AttackEffect.FIRE, true);
        }
    }

    @Override
    public void onEmpower() {
        upgradeMagicNumber(1);
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }

    @Override
    public String cardArtCopy() {
        return PiercingWail.ID;
    }
}