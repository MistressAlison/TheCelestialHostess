package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.cards.red.ShrugItOff;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static CelestialHostess.MainModfile.makeID;


public class Disengage extends AbstractEasyCard {
    public final static String ID = makeID(Disengage.class.getSimpleName());

    public Disengage() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL);
        baseBlock = block = 4;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.forAllMonstersLiving(mon -> Wiz.applyToEnemy(mon, new WeakPower(mon, magicNumber, false)));
    }

    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public String cardArtCopy() {
        return ShrugItOff.ID;
    }
}