package CelestialHostess.cards;

import CelestialHostess.cardmods.CorruptMod;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.powers.PurityPower;
import CelestialHostess.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.red.GhostlyArmor;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;


public class VoidSpirit extends AbstractEasyCard {
    public final static String ID = makeID(VoidSpirit.class.getSimpleName());

    public VoidSpirit() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
        CardModifierManager.addModifier(this, new CorruptMod(1));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new PurityPower(p, magicNumber));
    }

    public void upp() {
        selfRetain = true;
        uDesc();
    }

    @Override
    public String cardArtCopy() {
        return GhostlyArmor.ID;
    }
}