package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.ShrugItOff;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static CelestialHostess.MainModfile.makeID;

public class Taunt extends AbstractEasyCard {
    public final static String ID = makeID(Taunt.class.getSimpleName());

    public Taunt() {
        super(ID, 2, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF_AND_ENEMY);
        baseBlock = block = 7;
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("VO_CHAMP_2A"));
        blck();
        Wiz.applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
    }

    @Override
    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return ShrugItOff.ID;
    }

}