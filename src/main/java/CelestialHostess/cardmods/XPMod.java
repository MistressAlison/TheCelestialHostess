package CelestialHostess.cardmods;

import CelestialHostess.MainModfile;
import CelestialHostess.cards.abstracts.AbstractAbilityCard;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class XPMod extends AbstractCardModifier {
    public static final String ID = MainModfile.makeID(XPMod.class.getSimpleName());
    public static final String[] TEXT = CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION;

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        if (card instanceof AbstractAbilityCard) {
            return rawDescription + TEXT[0] + ((AbstractAbilityCard) card).currentXP + "/" + ((AbstractAbilityCard) card).xpForLevel();
        }
        return rawDescription;
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new XPMod();
    }
}
