package CelestialHostess.cards;

import CelestialHostess.actions.CleansePowerAction;
import CelestialHostess.cardmods.HolyMod;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.cards.abstracts.AbstractHolyInfoCard;
import CelestialHostess.util.Wiz;
import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.cards.purple.Pray;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static CelestialHostess.MainModfile.makeID;


public class Espoir extends AbstractEasyCard {
    public final static String ID = makeID(Espoir.class.getSimpleName());

    public Espoir() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 11;
        baseMagicNumber = magicNumber = 1;
        CardModifierManager.addModifier(this, new HolyMod());
        MultiCardPreview.add(this, new AbstractHolyInfoCard(this, cardStrings.EXTENDED_DESCRIPTION[0]) {});
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new CleansePowerAction(p, Wiz.auraActive() ? p.powers.size() : magicNumber, pow -> pow.type == AbstractPower.PowerType.DEBUFF));
    }

    public void upp() {
        upgradeBlock(4);
        MultiCardPreview.multiCardPreview.get(this).forEach(c -> {
            if (c instanceof AbstractHolyInfoCard) {
                c.upgrade();
            }
        });
    }

    @Override
    public String cardArtCopy() {
        return Pray.ID;
    }
}