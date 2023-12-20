package CelestialHostess.cards;

import CelestialHostess.cardmods.HolyMod;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.cards.abstracts.AbstractHolyInfoCard;
import CelestialHostess.patches.CustomTags;
import CelestialHostess.powers.interfaces.AuraTriggerPower;
import CelestialHostess.util.Wiz;
import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.purple.SimmeringFury;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static CelestialHostess.MainModfile.makeID;

public class Punishment extends AbstractEasyCard {
    public final static String ID = makeID(Punishment.class.getSimpleName());

    public Punishment() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 7;
        baseMagicNumber = magicNumber = 2;
        tags.add(CustomTags.HOSTESS_IF_MIRACLE);
        CardModifierManager.addModifier(this, new HolyMod(5, 0, 0));
        MultiCardPreview.add(this, new AbstractHolyInfoCard(this, cardStrings.EXTENDED_DESCRIPTION[0]) {
            @Override
            public int deltaDamage() {
                return 5;
            }
        });
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    @Override
    public void applyPowers() {
        int base = baseDamage;
        if (!Wiz.auraActive()) {
            for (AbstractPower p : Wiz.adp().powers) {
                if (p instanceof AuraTriggerPower) {
                    baseDamage += magicNumber * p.amount;
                }
            }
        }
        super.applyPowers();
        baseDamage = base;
        isDamageModified = baseDamage != damage;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int base = baseDamage;
        if (!Wiz.auraActive()) {
            for (AbstractPower p : Wiz.adp().powers) {
                if (p instanceof AuraTriggerPower) {
                    baseDamage += magicNumber * p.amount;
                }
            }
        }
        super.calculateCardDamage(mo);
        baseDamage = base;
        isDamageModified = baseDamage != damage;
    }

    @Override
    public void upp() {
        upgradeDamage(3);
        MultiCardPreview.multiCardPreview.get(this).forEach(c -> {
            if (c instanceof AbstractHolyInfoCard) {
                c.upgrade();
            }
        });
    }

    @Override
    public String cardArtCopy() {
        return SimmeringFury.ID;
    }
}