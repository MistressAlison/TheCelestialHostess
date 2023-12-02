package CelestialHostess.cards;

import CelestialHostess.cardmods.CorruptMod;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.powers.LightChargePower;
import CelestialHostess.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.blue.Storm;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class Lightmare extends AbstractEasyCard {
    public final static String ID = makeID(Lightmare.class.getSimpleName());

    public Lightmare() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
        CardModifierManager.addModifier(this, new CorruptMod(1));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new LightChargePower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Storm.ID;
    }
}