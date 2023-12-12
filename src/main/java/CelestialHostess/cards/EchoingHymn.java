package CelestialHostess.cards;

import CelestialHostess.cardmods.HolyMod;
import CelestialHostess.cards.abstracts.AbstractEasyCard;
import CelestialHostess.cards.abstracts.AbstractHolyInfoCard;
import CelestialHostess.powers.EchoingHymnPower;
import CelestialHostess.util.Wiz;
import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.cards.purple.BattleHymn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static CelestialHostess.MainModfile.makeID;

public class EchoingHymn extends AbstractEasyCard {
    public final static String ID = makeID(EchoingHymn.class.getSimpleName());

    public EchoingHymn() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 4;
        baseMagicNumber = magicNumber = 2;
        CardModifierManager.addModifier(this, new HolyMod(0, 0, 1));
        MultiCardPreview.add(this, new AbstractHolyInfoCard(this, cardStrings.EXTENDED_DESCRIPTION[0]) {
            @Override
            public int deltaMagic() {
                return 1;
            }
        });
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.applyToSelf(new EchoingHymnPower(p, block, magicNumber));
    }

    @Override
    public void upp() {
        upgradeBlock(2);
        MultiCardPreview.multiCardPreview.get(this).forEach(c -> {
            if (c instanceof AbstractHolyInfoCard) {
                c.upgrade();
            }
        });
    }

    @Override
    public String cardArtCopy() {
        return BattleHymn.ID;
    }
}