package CelestialHostess.cards.tokens;

import CelestialHostess.actions.HolyAction;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.patches.CustomTags;
import CelestialHostess.util.Wiz;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.blue.Glacier;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class FrostBlessing extends AbstractEasyCard {
    public final static String ID = makeID(FrostBlessing.class.getSimpleName());

    public FrostBlessing() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        baseBlock = block = 3;
        selfRetain = true;
        exhaust = true;
        tags.add(CustomTags.HOSTESS_HOLY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HolyAction(() -> Wiz.att(new GainBlockAction(p, p, block))));
    }

    @Override
    public void upp() {
        upgradeBlock(2);
    }

    @Override
    public String cardArtCopy() {
        return Glacier.ID;
    }
}