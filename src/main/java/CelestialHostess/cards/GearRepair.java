package CelestialHostess.cards;

import CelestialHostess.cardmods.TributeMod;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.cards.red.Armaments;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class GearRepair extends AbstractEasyCard {
    public final static String ID = makeID(GearRepair.class.getSimpleName());

    public GearRepair() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        exhaust = true;
        CardModifierManager.addModifier(this, new TributeMod(1));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //addToBot(new TributeAction(() -> addToTop(new ArmamentsAction(true))));
        addToBot(new ArmamentsAction(true));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    public String cardArtCopy() {
        return Armaments.ID;
    }
}