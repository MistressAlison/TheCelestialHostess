package CelestialHostess.cards;

import CelestialHostess.cardmods.HolyMod;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.cards.abstracts.AbstractHolyInfoCard;
import CelestialHostess.util.Wiz;
import basemod.ReflectionHacks;
import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.red.Havoc;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class EclipsePower extends AbstractEasyCard {
    public final static String ID = makeID(EclipsePower.class.getSimpleName());

    public EclipsePower() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 10;
        CardModifierManager.addModifier(this, new HolyMod());
        MultiCardPreview.add(this, new AbstractHolyInfoCard(this, cardStrings.EXTENDED_DESCRIPTION[0]) {});
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //addToBot(new BigExplosionVFX(m));
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int base = baseDamage;
        int bonus = 0;
        if (!mo.isDeadOrEscaped() && mo.getIntentBaseDmg() > 0 && Wiz.auraActive()) {
            bonus = ReflectionHacks.<Integer>getPrivate(mo, AbstractMonster.class, "intentDmg");
            if (ReflectionHacks.<Boolean>getPrivate(mo, AbstractMonster.class, "isMultiDmg"))
            {
                bonus *= ReflectionHacks.<Integer>getPrivate(mo, AbstractMonster.class, "intentMultiAmt");
            }
        }
        baseDamage += bonus;
        super.calculateCardDamage(mo);
        baseDamage = base;
        isDamageModified = baseDamage != damage;
    }

    @Override
    public void upp() {
        upgradeDamage(5);
        MultiCardPreview.multiCardPreview.get(this).forEach(c -> {
            if (c instanceof AbstractHolyInfoCard) {
                c.upgrade();
            }
        });
    }

    @Override
    public String cardArtCopy() {
        return Havoc.ID;
    }
}