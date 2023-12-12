package CelestialHostess.cards;

import CelestialHostess.cardmods.HolyMod;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.cards.abstracts.AbstractHolyInfoCard;
import CelestialHostess.powers.ChargePreservationPower;
import CelestialHostess.powers.interfaces.AuraTriggerPower;
import CelestialHostess.util.Wiz;
import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
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
        baseMagicNumber = magicNumber = 1;
        tags.add(CardTags.STRIKE);
        CardModifierManager.addModifier(this, new HolyMod());
        MultiCardPreview.add(this, new AbstractHolyInfoCard(this, cardStrings.EXTENDED_DESCRIPTION[0]) {});
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        //Wiz.applyToSelf(new ChargePreservationPower(p, magicNumber));
        AbstractPower preserve = Wiz.adp().getPower(ChargePreservationPower.POWER_ID);
        for (AbstractPower pow : Wiz.adp().powers) {
            if (pow instanceof AuraTriggerPower) {
                ((AuraTriggerPower) pow).onActivateAura();
                if (preserve == null && !Wiz.auraActive()) {
                    Wiz.atb(new RemoveSpecificPowerAction(Wiz.adp(), Wiz.adp(), pow));
                }
            }
        }
        if (preserve != null && !Wiz.auraActive()) {
            Wiz.atb(new ReducePowerAction(Wiz.adp(), Wiz.adp(), preserve, 1));
        }
    }

    @Override
    public void upp() {
        upgradeDamage(4);
        MultiCardPreview.multiCardPreview.get(this).forEach(c -> {
            if (c instanceof AbstractHolyInfoCard) {
                c.upgrade();
            }
        });
    }

    @Override
    public String cardArtCopy() {
        return SeverSoul.ID;
    }
}