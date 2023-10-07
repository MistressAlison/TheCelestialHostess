package CelestialHostess.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import static CelestialHostess.MainModfile.makeID;

public class RetainMod extends AbstractCardModifier {
    public static String ID = makeID(RetainMod.class.getSimpleName());
    public static String[] TEXT = CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION;

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return TEXT[0] + rawDescription;
    }

    public boolean shouldApply(AbstractCard card) {
        return !card.selfRetain;
    }

    public void onInitialApplication(AbstractCard card) {
        card.selfRetain = true;
    }

    public AbstractCardModifier makeCopy() {
        return new RetainMod();
    }

    public String identifier(AbstractCard card) {
        return ID;
    }
}
