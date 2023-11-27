package CelestialHostess.cards;

import CelestialHostess.cardmods.TributeMod;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.powers.PietyPower;
import CelestialHostess.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.purple.Crescendo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class Empower extends AbstractEasyCard {
    public final static String ID = makeID(Empower.class.getSimpleName());

    public Empower() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
        CardModifierManager.addModifier(this, new TributeMod(1));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //addToBot(new TributeAction(() -> Wiz.applyToSelfTop(new PietyPower(p, magicNumber))));
        Wiz.applyToSelfTop(new PietyPower(p, magicNumber));;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }

    @Override
    public String cardArtCopy() {
        return Crescendo.ID;
    }
}