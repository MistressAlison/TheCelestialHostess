package CelestialHostess.cards;

import CelestialHostess.cardmods.HolyMod;
import CelestialHostess.cardmods.TributeMod;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.cards.abstracts.AbstractHolyInfoCard;
import CelestialHostess.util.Wiz;
import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.Crescendo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static CelestialHostess.MainModfile.makeID;

public class Empower extends AbstractEasyCard {
    public final static String ID = makeID(Empower.class.getSimpleName());

    public Empower() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
        CardModifierManager.addModifier(this, new TributeMod(1));
        CardModifierManager.addModifier(this, new HolyMod(0, 0, -2));
        AbstractCard preview = new AbstractHolyInfoCard(this, cardStrings.EXTENDED_DESCRIPTION[0]) {
            @Override
            public int deltaMagic() {
                return -2;
            }
        };
        CardModifierManager.addModifier(preview, new TributeMod(1));
        MultiCardPreview.add(this, preview);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //addToBot(new TributeAction(() -> Wiz.applyToSelfTop(new PietyPower(p, magicNumber))));
        //Wiz.applyToSelf(new PietyPower(p, magicNumber));;
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
        if (!Wiz.auraActive()) {
            addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, magicNumber)));
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
        MultiCardPreview.multiCardPreview.get(this).forEach(c -> {
            if (c instanceof AbstractHolyInfoCard) {
                c.upgrade();
            }
        });
    }

    @Override
    public String cardArtCopy() {
        return Crescendo.ID;
    }
}