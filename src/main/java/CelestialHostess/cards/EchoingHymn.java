package CelestialHostess.cards;

import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.powers.EchoingHymnPower;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.cards.purple.BattleHymn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class EchoingHymn extends AbstractEasyCard {
    public final static String ID = makeID(EchoingHymn.class.getSimpleName());

    public EchoingHymn() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 5;
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.applyToSelf(new EchoingHymnPower(p, block, magicNumber));
    }

    @Override
    public void upp() {
        upgradeBlock(2);
    }

    @Override
    public String cardArtCopy() {
        return BattleHymn.ID;
    }
}