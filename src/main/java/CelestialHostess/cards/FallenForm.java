package CelestialHostess.cards;

import CelestialHostess.cardmods.CorruptMod;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.powers.FallenFormPower;
import CelestialHostess.util.Wiz;
import basemod.helpers.BaseModCardTags;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.red.Corruption;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class FallenForm extends AbstractEasyCard {
    public final static String ID = makeID(FallenForm.class.getSimpleName());

    public FallenForm() {
        super(ID, 0, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        tags.add(BaseModCardTags.FORM);
        CardModifierManager.addModifier(this, new CorruptMod(3));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new FallenFormPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Corruption.ID;
    }
}