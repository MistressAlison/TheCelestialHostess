package CelestialHostess.cards;

import CelestialHostess.cardmods.CorruptMod;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.purple.FearNoEvil;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbActivateEffect;

import static CelestialHostess.MainModfile.makeID;

public class DarkDesires extends AbstractEasyCard {
    public final static String ID = makeID(DarkDesires.class.getSimpleName());

    public DarkDesires() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 13;
        baseMagicNumber = magicNumber = 6;
        CardModifierManager.addModifier(this, new CorruptMod(1));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            addToBot(new SFXAction("ORB_DARK_EVOKE", 0.1F));
            addToBot(new VFXAction(new DarkOrbActivateEffect(m.hb.cX, m.hb.cY)));
        }
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int base = baseDamage;
        for (AbstractPower p : mo.powers) {
            if (p.type == AbstractPower.PowerType.BUFF) {
                baseDamage += magicNumber;
            }
        }
        super.calculateCardDamage(mo);
        baseDamage = base;
        isDamageModified = baseDamage != damage;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(3);
    }

    @Override
    public String cardArtCopy() {
        return FearNoEvil.ID;
    }
}